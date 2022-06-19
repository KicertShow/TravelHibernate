package backend.hotel.model.logging;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "Logging")
public class Logging {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int   id;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "USERPWD")
	private String userpwd;
	
	public Logging() {
		super();
	}
	public Logging(String username, String userpwd) {
		super();
		this.username = username;
		this.userpwd = userpwd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	
	
	
	
	
	
}
