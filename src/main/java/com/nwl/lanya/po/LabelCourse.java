package com.nwl.lanya.po;

public class LabelCourse {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column label_course.id
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column label_course.label_info_id
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    private String labelInfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column label_course.course_id
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    private String courseId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column label_course.id
     *
     * @return the value of label_course.id
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column label_course.id
     *
     * @param id the value for label_course.id
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column label_course.label_info_id
     *
     * @return the value of label_course.label_info_id
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    public String getLabelInfoId() {
        return labelInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column label_course.label_info_id
     *
     * @param labelInfoId the value for label_course.label_info_id
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    public void setLabelInfoId(String labelInfoId) {
        this.labelInfoId = labelInfoId == null ? null : labelInfoId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column label_course.course_id
     *
     * @return the value of label_course.course_id
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column label_course.course_id
     *
     * @param courseId the value for label_course.course_id
     *
     * @mbg.generated Thu Mar 04 10:08:32 CST 2021
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }
}