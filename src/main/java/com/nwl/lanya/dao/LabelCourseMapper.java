package com.nwl.lanya.dao;

import com.nwl.lanya.po.LabelCourse;
import com.nwl.lanya.po.LabelCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LabelCourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    long countByExample(LabelCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    int deleteByExample(LabelCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    int insert(LabelCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    int insertSelective(LabelCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    List<LabelCourse> selectByExample(LabelCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    LabelCourse selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    int updateByExampleSelective(@Param("record") LabelCourse record, @Param("example") LabelCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    int updateByExample(@Param("record") LabelCourse record, @Param("example") LabelCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    int updateByPrimaryKeySelective(LabelCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_course
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    int updateByPrimaryKey(LabelCourse record);
}