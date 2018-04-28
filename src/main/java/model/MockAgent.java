package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "Agent")
public class MockAgent{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Column (nullable = true)
	private String localizacion;
	private String email;
	
	@Column (unique = true, nullable = false)
	String identificador;
	
	@ManyToOne
	MockType tipo;

	
	MockAgent(){}

	public MockAgent(String nombre, String email, String identificador, MockType tipo) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.identificador = identificador;
		this.tipo = tipo;
	}
	
	public MockAgent(String nombre, String localizacion, String email, String identificador, MockType tipo) {
		super();
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.email = email;
		this.identificador = identificador;
		this.tipo = tipo;
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MockAgent other = (MockAgent) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public MockType getTipo() {
		return tipo;
	}
	
	
	
}

	
