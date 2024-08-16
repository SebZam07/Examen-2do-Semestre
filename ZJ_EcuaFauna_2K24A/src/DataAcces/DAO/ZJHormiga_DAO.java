package DataAcces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import DataAcces.ZJ_IDAO;
import DataAcces.ZJ_SQLiteDataHelper;
import DataAcces.DTO.ZJHormiga_DTO;
import Framework.ZJ_PatException;

public class ZJHormiga_DAO extends ZJ_SQLiteDataHelper implements ZJ_IDAO<ZJHormiga_DTO> {
    @Override
    public boolean zjCreate() throws Exception {
        String query = "INSERT INTO ZJHormiga (TipoHormiga, Sexo, Provincia, GenoAlimento, IngestaNativa) VALUES (?,?,?,?,?)";
        try {
            Connection conn = zjOpenConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "Larva");
            pstmt.setInt(2, 4);
            pstmt.setInt(3, zjSeleccionarProvincia());
            pstmt.setInt(4, 3);
            pstmt.setInt(5, 3);
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new ZJ_PatException(e.getMessage(), getClass().getName(), "create()");
        }
    }
    
    @Override
    public List<ZJHormiga_DTO> zjReadAll() throws Exception {
        List<ZJHormiga_DTO> lst = new ArrayList<>();
        String query = "SELECT ROW_NUMBER() OVER (ORDER BY IDHormiga) RowNum, "
                     + "h.IDHormiga, h.TipoHormiga, a1.Nombre, a2.Nombre, s.Nombre, l.Nombre, h.EstadoCondición "
                     + "FROM ZJHormiga h "
                     + "JOIN ZJAlimento a1 ON h.GenoAlimento = a1.IDAlimento "
                     + "JOIN ZJAlimento a2 ON h.IngestaNativa = a2.IDAlimento "
                     + "JOIN ZJSexo s ON h.Sexo = s.IdSexo "
                     + "JOIN ZJLocalidad l ON h.Provincia = l.IdLocalidad "
                     + "WHERE h.Estado = 'A'";
        try {
            Connection conn = zjOpenConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ZJHormiga_DTO s = new ZJHormiga_DTO(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8)
                );
                lst.add(s);
            }
        } 
        catch (SQLException e) {
            throw new ZJ_PatException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }
    
    @Override
    public boolean zjDelete(int id) throws Exception {
        String query = "UPDATE ZJHormiga SET Estado = 'X' WHERE IDHormiga = ?";
        try {
            Connection conn = zjOpenConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new ZJ_PatException(e.getMessage(), getClass().getName(), "delete()");
        }
    }
    
    @Override
    public ZJHormiga_DTO zjReadBy(Integer id) throws Exception {
        ZJHormiga_DTO s = new ZJHormiga_DTO();
        String query = "SELECT RowNum, sub.IDHormiga, sub.TipoHormiga, sub.NombreAlimento1, sub.NombreAlimento2, sub.Sexo, sub.provincia, sub.estadoCondicion "
                     + "FROM (SELECT ROW_NUMBER() OVER (ORDER BY h.IDHormiga) AS RowNum, h.IDHormiga, h.TipoHormiga, a1.Nombre AS NombreAlimento1, a2.Nombre AS NombreAlimento2, l.Nombre AS provincia, s.Nombre AS Sexo, h.EstadoCondición AS estadoCondicion "
                     + "FROM ZJHormiga h "
                     + "JOIN ZJAlimento a1 ON h.GenoAlimento = a1.IDAlimento "
                     + "JOIN ZJAlimento a2 ON h.IngestaNativa = a2.IDAlimento "
                     + "JOIN ZJSexo s ON h.Sexo = s.IdSexo "
                     + "JOIN ZJLocalidad l ON h.Provincia = l.IdLocalidad "
                     + "WHERE h.Estado = 'A') sub "
                     + "WHERE RowNum = " + id.toString();
        try {
            Connection conn = zjOpenConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(query);
            while (rs.next()) {
                s = new ZJHormiga_DTO(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8)
                );
            }
        } 
        catch (SQLException e) {
            throw new ZJ_PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return s;
    }
    
    @Override
    public Integer zjGetMaxRow() throws Exception {
        String query = "SELECT COUNT(*) TotalRegistros FROM ZJHormiga WHERE Estado = 'A'";
        try {
            Connection conn = zjOpenConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } 
        catch (SQLException e) {
            throw new ZJ_PatException(e.getMessage(), getClass().getName(), "getRowCount()");
        }
        return 0;
    }
    
    private int zjSeleccionarProvincia() {
        int min = 6;
        int max = 29;
        return (int) (Math.random() * (max - min + 1)) + min;
    }
    
    @Override
    public boolean zjUpdateGenoAlimento(ZJHormiga_DTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE ZJHormiga SET GenoAlimento = ?, Sexo = ?, FechaModifica = ? WHERE IDHormiga = ?";
        try {
            Connection conn = zjOpenConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getZjGenoAlimento());
            pstmt.setInt(2, verificarSexo(entity.getZjGenoAlimento()));
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getZjIDHormiga());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new ZJ_PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }
    
    @Override
    public boolean zjUpdateIngestaNativa(ZJHormiga_DTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE ZJHormiga SET IngestaNativa = ?, TipoHormiga = ?, FechaModifica = ? WHERE IDHormiga = ?";
        try {
            Connection conn = zjOpenConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getZjIngestaNativa());
            pstmt.setString(2, verificarTipo(entity.getZjIngestaNativa()));
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getZjIDHormiga());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new ZJ_PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }
    
    private String verificarTipo(Integer zjIngestaNativa) {
        if (zjIngestaNativa == 4) {
            return "Hormiga de la tierra";
        } else if (zjIngestaNativa == 5) {
            return "Hormiga de la madera";
        } else if (zjIngestaNativa == 6) {
            return "Hormiga de la hoja";
        } else if (zjIngestaNativa == 7) {
            return "Hormiga de la fruta";
        }
        return "no";
    }
    
    private int verificarSexo(Integer zjGenoAlimento) {
        if (zjGenoAlimento == 8) {
            return 3;
        } else if (zjGenoAlimento == 9) {
            return 2;
        } else if (zjGenoAlimento == 10) {
            return 1;
        }
        return -1;
    }
}
