package com.amber.mycommunity.service;

import com.amber.mycommunity.dto.PaginationDTO;
import com.amber.mycommunity.dto.QuestionDTO;
import com.amber.mycommunity.exception.CustomizeErrorCode;
import com.amber.mycommunity.exception.CustomizeException;
import com.amber.mycommunity.mapper.QuestionExtMapper;
import com.amber.mycommunity.mapper.QuestionMapper;
import com.amber.mycommunity.mapper.UserMapper;
import com.amber.mycommunity.model.Question;
import com.amber.mycommunity.model.QuestionExample;
import com.amber.mycommunity.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amber
 * @date 2021/7/27 17:10
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size){
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        //Integer totalCount= questionMapper.count();
        QuestionExample questionExample = new QuestionExample();
        Integer totalCount = (int) questionMapper.countByExample(questionExample);


        if (totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset =size*(page-1);

        //List<Question> questions = questionMapper.list(offset,size);
        QuestionExample example = new QuestionExample();
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : questions) {
            //User user = userMapper.findById(question.getCreator());
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setDescription("");
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);

        //Integer totalCount= questionMapper.countByUserId(userId);

        if (totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset =size*(page-1);

        //List<Question> questions = questionMapper.listByUserId(userId,offset,size);

        List<QuestionDTO> questionDTOList=new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }


    public QuestionDTO getById(Long id) {
        //Question question = questionMapper.getById(id);
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        //User user = userMapper.findById(question.getCreator());
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            // 创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        }else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long questionId) {
        /* 因为每次访问是从数据库中读取  当访问量大的时候会出现并发问题  可以使用乐观锁悲观锁  现阶段用累加解决
        Question question = questionMapper.selectByPrimaryKey(questionId);
        Question updateQuestion = new Question();
        updateQuestion.setId(questionId);
        updateQuestion.setViewCount(question.getViewCount()+1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andIdEqualTo(questionId);
        questionMapper.updateByExampleSelective(updateQuestion,questionExample);
         */
        Question question = new Question();
        question.setId(questionId);
        //递增1
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
