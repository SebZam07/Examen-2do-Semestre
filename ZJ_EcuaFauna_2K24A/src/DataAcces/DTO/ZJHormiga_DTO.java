package DataAcces.DTO;

public class ZJHormiga_DTO {
    private Integer zjRowNum;
    private Integer zjIDHormiga;
    private String  zjTipoHormiga;
    private Integer zjSexo;
    private Integer zjProvincia;
    private Integer zjGenoAlimento;
    private Integer zjIngestaNativa;

    private String zjNombreGenoAlimento;
    private String zjNombreIngestaNativa;
    private String zjNombreSexo;
    private String zjNombreProvincia;
    private String zjEstadoCondición;
    private String zjEstado;

    

    public ZJHormiga_DTO(String zjTipoHormiga) {
        this.zjTipoHormiga = zjTipoHormiga;
    }
    public ZJHormiga_DTO() {
    }
    public ZJHormiga_DTO(Integer zjRowNum, Integer zjIDHormiga, String zjTipoHormiga, String zjNombreGenoAlimento,
            String zjNombreIngestaNativa, String zjNombreSexo, String zjNombreProvincia, String zjEstadoCondición) {
        this.zjRowNum = zjRowNum;
        this.zjIDHormiga = zjIDHormiga;
        this.zjTipoHormiga = zjTipoHormiga;
        this.zjNombreGenoAlimento = zjNombreGenoAlimento;
        this.zjNombreIngestaNativa = zjNombreIngestaNativa;
        this.zjNombreSexo = zjNombreSexo;
        this.zjNombreProvincia = zjNombreProvincia;
        this.zjEstadoCondición = zjEstadoCondición;
    }
    public Integer getZjIDHormiga() {
        return zjIDHormiga;
    }
    public void setZjIDHormiga(Integer zjIDHormiga) {
        this.zjIDHormiga = zjIDHormiga;
    }
    public String getZjTipoHormiga() {
        return zjTipoHormiga;
    }
    public void setZjTipoHormiga(String zjTipoHormiga) {
        this.zjTipoHormiga = zjTipoHormiga;
    }
    public Integer getZjSexo() {
        return zjSexo;
    }
    public void setZjSexo(Integer zjSexo) {
        this.zjSexo = zjSexo;
    }
    public Integer getZjProvincia() {
        return zjProvincia;
    }
    public void setZjProvincia(Integer zjProvincia) {
        this.zjProvincia = zjProvincia;
    }
    public Integer getZjGenoAlimento() {
        return zjGenoAlimento;
    }
    
    public void setZjGenoAlimento(Integer zjGenoAlimento) {
        this.zjGenoAlimento = zjGenoAlimento;
    }
    
    public Integer getZjIngestaNativa() {
        return zjIngestaNativa;
    }
    
    public void setZjIngestaNativa(Integer zjIngestaNativa) {
        this.zjIngestaNativa = zjIngestaNativa;
    }
    
    public String getZjNombreSexo() {
        return zjNombreSexo;
    }
    
    public void setZjNombreSexo(String zjNombreSexo) {
        this.zjNombreSexo = zjNombreSexo;
    }
    
    public String getZjNombreProvincia() {
        return zjNombreProvincia;
    }
    
    public void setZjNombreProvincia(String zjNombreProvincia) {
        this.zjNombreProvincia = zjNombreProvincia;
    }
    
    public String getZjNombreGenoAlimento() {
        return zjNombreGenoAlimento;
    }
    
    public void setZjNombreGenoAlimento(String zjNombreGenoAlimento) {
        this.zjNombreGenoAlimento = zjNombreGenoAlimento;
    }
    
    public String getZjNombreIngestaNativa() {
        return zjNombreIngestaNativa;
    }
    
    public void setZjNombreIngestaNativa(String zjNombreIngestaNativa) {
        this.zjNombreIngestaNativa = zjNombreIngestaNativa;
    }
    
    public String getZjEstadoCondición() {
        return zjEstadoCondición;
    }
    
    public void setZjEstadoCondición(String zjEstadoCondición) {
        this.zjEstadoCondición = zjEstadoCondición;
    }
    
    public String getZjEstado() {
        return zjEstado;
    }
    
    public void setZjEstado(String zjEstado) {
        this.zjEstado = zjEstado;
    }
    
    public Integer getZjRowNum() {
        return zjRowNum;
    }
    
    public void setZjRowNum(Integer zjRowNum) {
        this.zjRowNum = zjRowNum;
    }
}
