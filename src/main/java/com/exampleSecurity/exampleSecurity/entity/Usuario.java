package com.exampleSecurity.exampleSecurity.entity;

	import java.io.Serializable;
	import java.util.ArrayList;
	import java.util.List;

	import javax.persistence.CascadeType;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.JoinTable;
	import javax.persistence.*;
	import javax.persistence.ManyToMany;
	import javax.persistence.ManyToOne;
	import javax.persistence.OneToMany;
	import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {



		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue
		private Long id;
		
		@Column(length =50, name="nombre")
		private String nombre;
		
		
		
		@Lob
		@Column(columnDefinition = "MEDIUMBLOB")
		private String imagen;
		

		
		
		
		@Column(length =50, name="correo")
		private String correo;	

		@Column(length =545, name="apellido")
		private String apellido;
		
		@Column(length =50, name="dni")
		private String dni;
		
		@Column(length =50, name="Edad")
		private Integer edad;	
		
		@Column(length =50, name="telefono")
		private Integer telefono;



		@ManyToOne
		@JoinColumn(name = "id_direccion")
		private Direccion direccion;		
		
		@ManyToMany
		@JoinTable(name = "usuario_proyecto",
		joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "id_proyecto", referencedColumnName = "id"))
		private List<Proyecto> proyectos = new ArrayList<>();

		@OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
		private List<Roll> roless = new ArrayList<Roll>();
	 	

		
		

	    
}
