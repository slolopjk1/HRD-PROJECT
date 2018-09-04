package dto;

public class Item_ListVO {
	private String Item_num;
	private String Item_name;
	private String SN;
	private String Manufacture;
	private String Model_name;
	private String Standard;
	private int dep_code;
	private String Use_where;
	private byte[] Image;
	private String Get_date;
	private String Pro_date;
	private int Get_cost;
	private String depname;
	private int count;
	public String getItem_num() {
		return Item_num;
	}
	public void setItem_num(String item_num) {
		Item_num = item_num;
	}
	public String getItem_name() {
		return Item_name;
	}
	public void setItem_name(String item_name) {
		Item_name = item_name;
	}
	public String getSN() {
		return SN;
	}
	public void setSN(String sN) {
		SN = sN;
	}
	public String getManufacture() {
		return Manufacture;
	}
	public void setManufacture(String manufacture) {
		Manufacture = manufacture;
	}
	public String getModel_name() {
		return Model_name;
	}
	public void setModel_name(String model_name) {
		Model_name = model_name;
	}
	public String getStandard() {
		return Standard;
	}
	public void setStandard(String standard) {
		Standard = standard;
	}
	public int getDep_code() {
		return dep_code;
	}
	public void setDep_code(int dep_code) {
		this.dep_code = dep_code;
	}
	public String getUse_where() {
		return Use_where;
	}
	public void setUse_where(String use_where) {
		Use_where = use_where;
	}
	public byte[] getImage() {
		return Image;
	}
	public void setImage(byte[] image) {
		Image = image;
	}
	public String getGet_date() {
		return Get_date;
	}
	public void setGet_date(String get_date) {
		Get_date = get_date;
	}
	public String getPro_date() {
		return Pro_date;
	}
	public void setPro_date(String pro_date) {
		Pro_date = pro_date;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getGet_cost() {
		return Get_cost;
	}
	public void setGet_cost(int get_cost) {
		Get_cost = get_cost;
	}
}