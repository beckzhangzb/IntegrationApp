package com.wallaw.study.cache.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class User implements Externalizable {
	private static final long serialVersionUID = 1669161354494542883L;
	
	private int id;
    private transient String name;
    private String address;
    
    public User() {
    	
	}
    public User(int id, String name, String address) {
    	this.id= id;
    	this.name = name;
    	this.address = address;
	}
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    	out.writeObject(id);
        out.writeObject(name);
        out.writeObject(address);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        id = (Integer) in.readObject();
        name = (String) in.readObject();
        address = (String) in.readObject();
    }
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}