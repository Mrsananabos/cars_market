package ru.job4j.start;


public class Item {
   
   private String id;
   public String name;
   public String desc;
   public long created;
   public String comments;


	public Item (String name, String desc, long created, String comments) {
		this.name = name;
		this.desc=desc;
		this.created=created;
		this.comments=comments;
		}
	
	public String getId() {
		return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}



	public String getName() {
		return this.name;
		}

	public String getDesc() {
		return this.desc;
		}

	public long getCreated() {
		return this.created;
		}

	public String getComment() {
		return this.comments;
		}
}
	
   
