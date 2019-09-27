package cn.com.edu.nyist.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_time")//上课时间表
public class Time implements Serializable{

	  private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue
	    private  Integer id;
	    private String name;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	    
}
