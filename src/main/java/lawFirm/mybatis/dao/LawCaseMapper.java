package lawFirm.mybatis.dao;

import java.util.List;
import lawFirm.mybatis.model.LawCase;
import org.mybatis.cdi.Mapper;


@Mapper
public interface LawCaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LAW_CASE
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    int deleteByPrimaryKey(Long id);
    List<LawCase> selectByLawyerId(Long lawyerId);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LAW_CASE
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    int insert(LawCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LAW_CASE
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    LawCase selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LAW_CASE
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    List<LawCase> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LAW_CASE
     *
     * @mbg.generated Sat Apr 12 23:15:16 EEST 2025
     */
    int updateByPrimaryKey(LawCase record);
}