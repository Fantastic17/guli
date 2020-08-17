package com.atguigu.guli.service.edu.listner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.guli.service.edu.entity.Subject;
import com.atguigu.guli.service.edu.entity.excel.ExcelSubjectData;
import com.atguigu.guli.service.edu.mapper.SubjectMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wind
 * @create 2020-07-04 18:02
 */
@Slf4j
@AllArgsConstructor //全参构造函数
@NoArgsConstructor //无参构造函数
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData>{

    private SubjectMapper subjectMapper;

    /**
     * 遍历每一行记录
     * @param data
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext analysisContext) {
        log.info("解析一条数据：{}",data);
        // 处理读取出来的数据
        String levelOneTitle = data.getLevelOneTitle(); //一级标题
        String levelTwoTitle = data.getLevelTwoTitle(); //二级标题
        log.info("levelOneTitle：{}",levelOneTitle);
        log.info("levelTwoTitle：{}",levelTwoTitle);


        //判断数据是否存在
        Subject subjectLevelOne = this.getByTitle(levelOneTitle);
        String parentId = null;
        if (subjectLevelOne == null) {
            //组装一级类别
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            //存储到数据库
            subjectMapper.insert(subject);
            parentId = subject.getId();
        }else{
            parentId = subjectLevelOne.getId();
        }

        //判断数据是否存在
        Subject subjectLevelTwo = this.getSubByTitle(levelTwoTitle, parentId);
        if (subjectLevelTwo == null) {
            //组装二级类别
            Subject subject = new Subject();
            subject.setTitle(levelTwoTitle);
            subject.setParentId(parentId);
            //存储到数据库
            subjectMapper.insert(subject);
        }
    }

    /**
     * 所有数据的收尾工作
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部数据解析完成");
    }


    /**
     * 根据一级分类的名称查询数据是否存在
     * @param title
     * @return
     */
    private Subject getByTitle(String title){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        queryWrapper.eq("parent_id", 0);//一级分类
        return subjectMapper.selectOne(queryWrapper);
    }


    /**
     * 根据分类名称以及父id查询数据是否存在
     * @param title
     * @param parentId
     * @return
     */
    private Subject getSubByTitle(String title,String parentId){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        queryWrapper.eq("parent_id", parentId);//二级分类
        return subjectMapper.selectOne(queryWrapper);
    }


}
