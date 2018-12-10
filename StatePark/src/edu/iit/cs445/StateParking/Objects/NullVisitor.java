package edu.iit.cs445.StateParking.Objects;

public class NullVisitor implements Visitor{
	private static NullVisitor instance = null;
	private NullVisitor() {}
	public static Visitor getinstance() {
		if(instance == null)
			instance = new NullVisitor();
		return instance;
	}
	@Override
	public int getVid() {
		return -1;
	}
	@Override
	public String getName() {
		return null;
	}
	@Override
	public String getEmail() {
		return null;
	}
	@Override
	public boolean KeywordMatch(String keyword) {
		return false;
	}
	@Override
	public boolean isNull() {
		return true;
	}
}
