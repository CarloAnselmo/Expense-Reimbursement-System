import java.io.InputStream;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.web.model.Reimbursement;
import com.web.model.ReimbursementDTO;
import com.web.model.ReimbursementStatus;
import com.web.model.ReimbursementType;
import com.web.model.User;
import com.web.model.UserDTO;
import com.web.model.UserRole;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModelTest {
	
	private Reimbursement r;
	private User u;
	private ReimbursementStatus rs;
	private ReimbursementType rt;
	private UserRole ur;
	private ReimbursementDTO rdto;
	private UserDTO udto;

	@Before
	public void setup() {
		r = new Reimbursement(1,1,null,null,"a",null,null,null,null,null);
		u = new User(1, "a", "a", "a", "a", "a", null);
		rs = new ReimbursementStatus(1, "a");
		rt = new ReimbursementType(1, "a");
		ur = new UserRole(1,"a");
		rdto = new ReimbursementDTO();
		udto = new UserDTO();
	}
	
	@Test
	public void RoleGetSet() {
		ur.setId(1);
		ur.setRole("a");
		
		ur.getId();
		ur.getRole();
		
		ur.toString();
	}
	
	@Test
	public void UserSetGet() {
		u.getEmail();
		u.getFirstname();
		u.getId();
		u.getLastname();
		u.getPassword();
		u.getRole_id();
		u.getUsername();
		
		u.setEmail("a");
		u.setFirstname("a");
		u.setId(1);
		u.setLastname("a");
		u.setPassword("a");
		u.setRole_id(null);
		u.setUsername("a");
		
		u.toString();
	}
	
	@Test
	public void StatusSetGet() {
		rs.setId(1);
		rs.setStatus("a");
		
		rs.getId();
		rs.getStatus();
		
		rs.toString();
	}
	
	@Test
	public void TypeSetGet() {
		rt.setId(1);
		rt.setType("a");
		
		rt.getId();
		rt.getType();
		
		rt.toString();
	}
	
	@Test
	public void ReimbSetGet() {
		r.getId();
		r.getAmount();
		r.getAuthor();
		r.getClass();
		r.getDescription();
		r.getReceipt();
		r.getResolved();
		r.getResolver();
		r.getStatus_id();
		r.getSubmitted();
		r.getType_id();
		
		r.setId(1);
		r.setAmount(1);
		r.setAuthor(null);
		r.setDescription("a");
		r.setReceipt(null);
		r.setResolved(null);
		r.setResolver(null);
		r.setStatus_id(null);
		r.setSubmitted(null);
		r.setType_id(null);
		
		r.toString();
	}
	
	@Test
	public void ReimbDTOGetSet() {
		ReimbursementDTO c1 = new ReimbursementDTO(1,1,null,null,"a",null,"a","a","a","a");
		ReimbursementDTO c2 = new ReimbursementDTO(1,"a",null,"a","a");
		ReimbursementDTO c3 = new ReimbursementDTO(1,"a",null,1,1);
		
		rdto.setAmount(1);
		rdto.setAuthor("a");
		rdto.setAuthor_id(1);
		rdto.setDescription("a");
		rdto.setId(1);
		rdto.setReceipt(null);
		rdto.setResolved(null);
		rdto.setResolver("a");
		rdto.setResolver_id(1);
		rdto.setStatus("a");
		rdto.setStatus_id(1);
		rdto.setSubmitted(null);
		rdto.setType("a");
		rdto.setType_id(1);
		
		rdto.getAmount();
		rdto.getAuthor();
		rdto.getAuthor_id();
		rdto.getDescription();
		rdto.getId();
		rdto.getReceipt();
		rdto.getResolved();
		rdto.getResolver();
		rdto.getResolver_id();
		rdto.getStatus();
		rdto.getStatus_id();
		rdto.getSubmitted();
		rdto.getType();
		rdto.getType_id();
		
		rdto.toString();
	}
	
	@Test
	public void UserDTOGetSet() {
		UserDTO c1 = new UserDTO(1, "a", "a", "a", "a", "a", "a");
		UserDTO c2 = new UserDTO("a", "a", "a", "a", "a");
		
		udto.getEmail();
		udto.getFirstname();
		udto.getId();
		udto.getLastname();
		udto.getPassword();
		udto.getRole();
		udto.getRole_id();
		udto.getUsername();
		
		udto.setEmail("a");
		udto.setFirstname("a");
		udto.setId(1);
		udto.setLastname("a");
		udto.setPassword("a");
		udto.setRole("a");
		udto.setRole_id(1);
		udto.setUsername("a");
		
		udto.toString();
	}

}
