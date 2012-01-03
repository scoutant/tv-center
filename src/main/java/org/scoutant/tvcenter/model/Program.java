package org.scoutant.tvcenter.model;

import java.io.Serializable;

import org.xml.sax.Attributes;

public class Program implements Serializable {
	private static final long serialVersionUID = 7371163189332273738L;
	
	public Program(){
	}
	public Program(Attributes atts){
		this();
		start = atts.getValue("start");
		stop = atts.getValue("stop");
		channel = atts.getValue("channel");
	}
	
	public String start; 
	public String stop;
	public String channel;
	public String title;
	public String subtitle;
	public String year;
	public String desc = "";
	public String credits = "";
	public String country;

	@Override
	public String toString() {
		String str = "Program [ ";
		str += "start=" + start + ", stop=" + stop + ", channel="
				+ channel + ", title=" + title + ", subtitle=" + subtitle
				+ ", credits=" + credits + ", year=" + year ;
		str += ", title : " + title;
		str += ", subtitle : " + subtitle;
		str += ", desc: " + desc;
		str += ", country: " + country;
		return str + " ]";
	}

	
}


/*
<programme start="20120110114000 +0100" stop="20120110115000 +0100" channel="C195.telepoche.com">
<title>Les zinzins de l'espace</title>
<sub-title>Brosse-toi les dents !</sub-title>
<desc lang="fr">Dévorant du matin au soir une quantité impressionnante de gâteaux et bonbons en tous genres, Gorgious est atteint d'une véritable boulimie de sucreries. Ses nuits sont peuplées de cauchemars, où il mange la maison de pain d'épice d'Hansel et Gretel, et se prend pour le Petit Chaperon Rouge. Constatant avec effroi que Georgious commence à perdre ses dents, minées par le sucre, ses compagnons prennent les choses en main. Toutes les sucreries soigneusement cachées par Gorgious sont détruites. En manque, Gorgious est en proie à des hallucinations. Devant l'étendue de ce délirium sucré, les monstres n'ont pas d'autre ressource que l'isolation totale du sujet contaminé</desc>
<desc lang="fr">Les zinzins décident de guérir Gorgious de son amour pour les sucreries.</desc>
<credits>
  <director>Thomas Szabo</director>
</credits>
<category lang="fr">dessin animé</category>
<country lang="fr">France</country>
<country>FR</country>
</programme>


  <programme start="20120110123500 +0100" stop="20120110133000 +0100" channel="C195.telepoche.com">
    <title>Dr Quinn, femme médecin</title>
    <title lang="en">Dr. Quinn, Medicine Woman</title>
    <sub-title>Mort ou vif</sub-title>
    <desc lang="fr">Erza Leenard, porteur d'une déclaration demandant que le territoire du Colorado soit érigé en Etat, s'arrête à Colorado Springs avant d'aller remettre le document à Washington. Pendant qu'il prononce son discours, son fils Caleb est enlevé par un certain McBride, opposé à ce projet</desc>
    <desc lang="fr">Erza Leenard, porteur d'une déclaration demandant que le territoire du Colorado soit érigé en État, s'arrête à Colorado Springs avant d'aller remettre le document à Washington</desc>
    <credits>
      <director>Chuck Bowman</director>
      <actor>Carl Binder</actor>
      <actor>Sullivan Company</actor>
      <actor>Columbia Broadcasting System (CBS)</actor>
    </credits>
    <date>1995</date>
    <category lang="fr">série/feuilleton</category>
    <category lang="fr">drame</category>
    <country lang="fr">Etats-Unis</country>
    <country>US</country>
    <video>
      <colour>yes</colour>
    </video>
  </programme>

*/