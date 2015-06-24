package de.dataport.network;

import java.io.Serializable;

public class Client implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name, ip;
	int Version;

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
		
	}

	public Client(String name, int version)
	{
		// TODO Auto-generated constructor stub
		this.name = name;
		this.Version = version;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}