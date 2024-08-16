package DataAcces;

import java.util.List;

public interface ZJ_IDAO <T> {
    public boolean zjCreate()     throws Exception;
    public List<T> zjReadAll()            throws Exception;
    public boolean zjUpdateGenoAlimento(T entity)     throws Exception;
    public boolean zjUpdateIngestaNativa(T entity)     throws Exception;
    public boolean zjDelete(int id)       throws Exception;
    public T       zjReadBy(Integer id)   throws Exception;
    public Integer zjGetMaxRow()          throws Exception;
}
