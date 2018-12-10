package edu.iit.cs445.StateParking.REST;


import edu.iit.cs445.StateParking.Objects.*;



public class SimpleParkPresenter {
	public String pid;
	public location_info location_info;
	public SimpleParkPresenter(ParkBoundaryInterface park) {
		this.pid = Integer.toString(park.getPid());
		this.location_info = new location_info(park);		
	}

	public class location_info{
		public String name;
		public String address;
		public String phone;
		public String web;
		public String region;
		public geo geo;
		public location_info(ParkBoundaryInterface park) {
			this.name = park.getName();
			String a = park.getAddress().getStreet()+","+
					park.getAddress().getCity()+", "+park.getAddress().getState().toString()+
					" "+park.getAddress().getZipcode();
			this.address = a;
			this.phone = park.getPhone();
			this.web = park.getWeb();
			this.region = park.getRegion();
			this.geo = park.getGeo();
		}

	}
}
