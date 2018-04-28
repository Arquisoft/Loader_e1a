package model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import util.FilesManager;

@Entity
@Table(name = "agent")
public class Agent {

	// Id generado automáticamente para diferenciar cada uno (para mapear)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Atributos del agente
	private String nombre;
	private String location;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String identifier;
	private String password;
	@Transient
	private int kindcode;
	
	private String kind;

	/**
	 * Constructor vacío (ya que es para mapear)
	 */
	Agent() {
	}

	/**
	 * @param nombre
	 * @param location
	 * @param email
	 * @param password
	 * @param kind
	 * @param identifier
	 */
	public Agent(String nombre, String localizacion, String email, String identificador, String tipo) {
		this.nombre =nombre;
		this.location =localizacion;
		setEmail(email);
		this.identifier = identificador;
		this.kind = tipo;
		generarPassword();
		try {
			kindcode = FilesManager.getKindCode(getKind());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Agent(String nombre, String email, String identificador, String tipo) {
		this.nombre =nombre;
		setEmail(email);
		this.identifier = identificador;
		this.kind = tipo;
		generarPassword();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}
	
	
	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getKind() {
		return kind;
	}

	public String getLocation() {
		return location;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Agent [nombre=" + nombre + ", localizacion=" + location + ", email=" + email + ", password="
				+ password + ", identificador=" + identifier + ", tipo=" + kind + "]";
	}
	private void generarPassword() {
		StringBuffer pass = new StringBuffer();
		int low = 65;
		int top = 90;
		for (int i = 0; i < 9; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (top - low) + low);
			pass.append((char) numAleatorio);
		}
		for (int i = 0; i < 3; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (9 - 0) + 0);
			pass.append(numAleatorio);
		}
		setPassword(pass.toString());
	}
}
