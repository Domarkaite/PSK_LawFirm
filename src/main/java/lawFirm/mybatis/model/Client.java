package lawFirm.mybatis.model;

public class Client {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CLIENT.ID
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CLIENT.EMAIL
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CLIENT.FULL_NAME
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    private String fullName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CLIENT.ID
     *
     * @return the value of PUBLIC.CLIENT.ID
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CLIENT.ID
     *
     * @param id the value for PUBLIC.CLIENT.ID
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CLIENT.EMAIL
     *
     * @return the value of PUBLIC.CLIENT.EMAIL
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CLIENT.EMAIL
     *
     * @param email the value for PUBLIC.CLIENT.EMAIL
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CLIENT.FULL_NAME
     *
     * @return the value of PUBLIC.CLIENT.FULL_NAME
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CLIENT.FULL_NAME
     *
     * @param fullName the value for PUBLIC.CLIENT.FULL_NAME
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}