package Business.BusinessLogic;

import java.util.List;

import DataAcces.DAO.ZJHormiga_DAO;
import DataAcces.DTO.ZJHormiga_DTO;

public class ZJ_HormigaBL {
    private ZJHormiga_DTO zjHormiga_DTO;
    private ZJHormiga_DAO zjHormiga_DAO = new ZJHormiga_DAO();
    
    public boolean zjAdd() throws Exception {
        return zjHormiga_DAO.zjCreate();
    }
    
    public boolean zjDelete(int idSexo) throws Exception {
        return zjHormiga_DAO.zjDelete(idSexo);
    }
    
    public Integer zjGetRowCount() throws Exception {
        return zjHormiga_DAO.zjGetMaxRow();
    }
    
    public List<ZJHormiga_DTO> getAll() throws Exception {
        List<ZJHormiga_DTO> lst = zjHormiga_DAO.zjReadAll();
        for (ZJHormiga_DTO hormigaDTO : lst)
            hormigaDTO.setZjTipoHormiga(hormigaDTO.getZjTipoHormiga().toUpperCase());
        return lst;
    }
    
    public ZJHormiga_DTO zjGetBy(int idSexo) throws Exception {
        zjHormiga_DTO = zjHormiga_DAO.zjReadBy(idSexo);
        return zjHormiga_DTO;
    }
    
    public boolean zjUpdateGenAliment(ZJHormiga_DTO hormigaDTO) throws Exception {
        return zjHormiga_DAO.zjUpdateGenoAlimento(hormigaDTO);
    }
    
    public boolean zjUpdateIngAliment(ZJHormiga_DTO hormigaDTO) throws Exception {
        return zjHormiga_DAO.zjUpdateIngestaNativa(hormigaDTO);
    }
}
