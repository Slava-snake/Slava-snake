package ua.khpi.oop;

import java.io.Serializable;

public class Agents implements Serializable{
	private static final long serialVersionUID = 1L;
	public int count;
	private int capacity=100;
	public Agent[] agent;
	
	public Agents() {
		agent = new Agent[capacity];
	}
	public Agent getAgentByIndex(int index) {
		if (validIndex(index)) return agent[index];
		return null;
	}
/*	public int findAgent(Agent a) {//-1=no such; -2=two match...
		int result=0;
		int once=0;
		for (int i=0; i<count; i++)
			if ((agent[i].name.indexOf(a.name)>=0)&&
				(agent[i].address.indexOf(a.address))&&
				(agent[i].phoneFax.indexOf(a.phoneFax))&&
				(agent[i].e_mail.indexOf(a.e_mail))) {
				result=i;
				once++;
			}
		return (once==0) ? -1 : (once==1)?result:-once;
	}
	public boolean onlyOne(Agent a) {
		return findAgent(a)>=0;
	}
	*/
	public Agent findAgent(String n, String a, String p, String e) {//-1=no such; -2=two match...
		for (int i=0; i<count; i++)
			if ((agent[i].name.indexOf(n)>=0)&&
				(agent[i].address.indexOf(a)>=0)&&
				(agent[i].phoneFax.indexOf(p)>=0)&&
				(agent[i].e_mail.indexOf(e)>=0)) {
				return agent[i];
			}
		return null;
	}
	private void resize() {
		if (count==capacity) {
			capacity*=2;
			Agent[] a = new Agent[capacity];
			agent = a;
		}
	}
/*public int add(Agent a) {
		if (findAgent(a)==-1) {
			resize();
			agent[count]=a;
			return count++;
		}
		else return -1;
	}*/
	public Agent add() {
		resize();
		agent[count]=new Agent(count++);
		return agent[count-1];
	}
	private boolean validIndex(int n) {
		return (n>=0)&&(n<count);
	}
	public String asString(int index) {
		if (validIndex(index)) return agent[index].asString();
		return "";
	}
}

