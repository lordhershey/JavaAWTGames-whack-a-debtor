import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.util.*;
import java.net.*;


/**********************************************************************/
/*                             Class neon                             */
/**********************************************************************/

final class neon
{

  WhackaDebtor w;

  int p;
  int counter;

  public neon(WhackaDebtor wad)
  {
    w=wad;
    p=0;
    counter = 200 + (int)(w.rand.nextDouble()*250);
  }

  public void move()
  {
    counter --;
    if(counter<0)
      {
	counter = 0;
	if(p==0)
	  {
	    p++;
	  }
	else
	  if(p==8)
	    {
	      p++;
	      counter = 100+ (int)(w.rand.nextDouble()*100);
	    }
	  else
	    if (p>=9)
	      {
		p=0;
		counter = 200 + (int)(w.rand.nextDouble()*250);
	      }
	    else
	      {
		counter = 1+(int)(w.rand.nextDouble()*3);
		p++;
	      }
      }/*if counted down*/

  }

  public void drawMe(Graphics g)
  {
    switch(p)
      {
      case 1:
	/*g.drawImage(w.nf[1],158,71,null);*/
	/*
	g.drawImage(w.backbuff2,0,0,null);
	break;
	*/
      case 3:
	/*g.drawImage(w.nf[1],158,71,null);*/
	/*
	g.drawImage(w.backbuff2,0,0,null);
	break;
	*/
      case 5:
	/*g.drawImage(w.nf[1],158,71,null);*/
	/*
	g.drawImage(w.backbuff2,0,0,null);
	break;
	*/
      case 7:
	/*g.drawImage(w.nf[1],158,71,null);*/
	/*
	g.drawImage(w.backbuff2,0,0,null);
	break;
	*/
      case 9:
	/*g.drawImage(w.nf[1],158,71,null);*/
	g.drawImage(w.backbuff2,0,0,null);
	break;
	
      default:
	/*g.drawImage(w.nf[0],158,71,null);*/
	g.drawImage(w.backbuff,0,0,null);
	break;
      }
  }

}

/**********************************************************************/
/*                              Class mallet                          */
/**********************************************************************/

final class mallet
{
  int delay;
  int bottomdelay;
  int counter;
  public int tx,ty; //target coordinates
  int p;     //phase

  WhackaDebtor w; //

  public mallet(WhackaDebtor wad)
  {

    w = wad;

    tx=0;
    ty=0;
    delay = 0;
    counter = 0;
    bottomdelay = 10;
    p = 0;
  }//constructor

  void setT(int x,int y)
  {
    /*Check to see if mallet is in motion*/
    if(p!=0)
      {
	return;
      }

    p=1;
    counter = 0;
    tx = x;
    ty = y;
  }

  void setC(int x,int y)
  {
    if(p!=0)
      return;

    tx=x;
    ty=y;
  }

  void move()
  {

    if(p!=0)
      {
	counter ++;
	if(counter >= delay)
	  {
	    if(p<3)
	      {
		counter = 0;
		p++;
		if(p==3)
		  {
		    w.hit = true;
		    w.hitx=tx;
		    w.hity=ty;
		  }
	      }
	    else
	      {
		if(counter >= bottomdelay)
		  {
		    counter = 0;
		    p = 0;
		    /*
		    w.hit = true;
		    w.hitx=tx;
		    w.hity=ty;
		    */
		  }
	      }   
	  }
      }/*end if need to move*/
  }

  void drawMe(Graphics g)
  {
    switch (p)
      {
      case 1:
	g.drawImage(w.mt[1],tx-10,ty-225,null);
	g.drawImage(w.mt[2],tx-34,ty-195,null);
	break;
      case 2:
	g.drawImage(w.mt[3],tx-40,ty-150,null);
	g.drawImage(w.mt[4],tx-40,ty-126,null);
	break;
      case 3:
	g.drawImage(w.mt[5],tx-37,ty-89,null);
	break;
      default:
	g.drawImage(w.mt[0],tx+37,ty-226,null);
	break;
      }
  }

  void setDelay(int d)
  {
    delay = d;
  }

  void setBottomDelay(int d)
  {
    bottomdelay = d;
  }

}//end mallet class

/**********************************************************************/
/*                            Class Numbers                           */
/**********************************************************************/

final class gnumber
{

  WhackaDebtor wad;

  int numvalue=0;

  int[] n;
  int [] w;
  
  public boolean redNumber = false;

  public gnumber(WhackaDebtor wad)
  {
    numvalue = 0;
    this.wad = wad;
    n=new int[11];
    redNumber = false;
    w=new int[10];
    w[0]=18;
    w[1]=15;
    w[2]=17;
    w[3]=16;
    w[4]=18;
    w[5]=16;
    w[6]=16;
    w[7]=17;
    w[8]=17;
    w[9]=16;
    setValue(numvalue);
  }

  public void setValue(int a)
  {
    int i,j,k;
    numvalue = a;

    redNumber = false;

    if(numvalue<-1000000)
      numvalue = -1000000;

    if(numvalue>1000000)
      numvalue = 1000000;

    j=numvalue;

    if(j<0)
      {
	j = -j;
	redNumber = true;
      }

    for(i=0,k=1000000;i<7;i++)
      {
	n[i]=j / k;
	j = j % k;
	k = k/10;
      }
    n[7]=-1;

    while(n[0]==0 && n[1]!=-1)
      {
	for(i=0;i<7;i++)
	  {
	    n[i]=n[(i+1)];
	  }
      }/*end while loop*/
  }

  public void addValue(int a)
  {
    numvalue+=a;
    setValue(numvalue);
  }

  public void drawMe(Graphics g)
  {
    int tx=449;
    int i;
    for(i=0;i<7 && n[i]!=-1 ;i++)
      {
	if(redNumber)
	  g.drawImage(wad.rn[n[i]],tx,34,null);
	else
	  g.drawImage(wad.gn[n[i]],tx,34,null);
	tx+=w[n[i]];
      }

  }

}

final class gnumber2
{

  WhackaDebtor wad;

  int numvalue=0;

  int[] n;
  int [] w;
  
  public boolean redNumber = false;
  int x,y;
  boolean flip;

  public gnumber2(WhackaDebtor wad,int x,int y,boolean flip)
  {
    this.flip=flip;
    this.x=x;
    this.y=y;
    numvalue = 0;
    this.wad = wad;
    n=new int[11];
    redNumber = false;
    w=new int[10];
    w[0]=18;
    w[1]=15;
    w[2]=17;
    w[3]=16;
    w[4]=18;
    w[5]=16;
    w[6]=16;
    w[7]=17;
    w[8]=17;
    w[9]=16;
    setValue(numvalue);
  }

  public void setValue(int a)
  {
    int i,j,k;
    numvalue = a;

    redNumber = false;

    if(numvalue<-999)
      numvalue = -999;

    if(numvalue>999)
      numvalue = 999;

    j=numvalue;

    if(j<0)
      {
	j = -j;
	redNumber = true;
      }

    for(i=0,k=100;i<3;i++)
      {
	n[i]=j / k;
	j = j % k;
	k = k/10;
      }
    n[3]=-1;

    while(n[0]==0 && n[1]!=-1)
      {
	for(i=0;i<3;i++)
	  {
	    n[i]=n[(i+1)];
	  }
      }/*end while loop*/
  }

  public void addValue(int a)
  {
    numvalue+=a;
    setValue(numvalue);
  }

  public void drawMe(Graphics g)
  {
    int tx=x;
    int i;

    for(i=0;i<3 && n[i]!=-1 ;i++)
      {
	if((!redNumber&&!flip) || (redNumber&&flip))
	  g.drawImage(wad.gn[n[i]],tx,y,null);
	else
	  g.drawImage(wad.rn[n[i]],tx,y,null);
	
	tx+=w[n[i]];
      }

  }

}

final class gnumber3
{

  WhackaDebtor wad;

  int numvalue=0;

  int[] n;
  int [] w;
  
  public boolean redNumber = false;
  int x,y;

  public gnumber3(WhackaDebtor wad,int x,int y)
  {
    this.x=x;
    this.y=y;
    numvalue = 0;
    this.wad = wad;
    n=new int[11];
    redNumber = false;
    w=new int[10];
    w[0]=18;
    w[1]=15;
    w[2]=17;
    w[3]=16;
    w[4]=18;
    w[5]=16;
    w[6]=16;
    w[7]=17;
    w[8]=17;
    w[9]=16;
    setValue(numvalue);
  }

  public void setValue(int a)
  {
    int i,j,k;
    numvalue = a;

    redNumber = false;

    if(numvalue<-99)
      numvalue = -99;

    if(numvalue>99)
      numvalue = 99;

    j=numvalue;

    if(j<0)
      {
	j = -j;
	redNumber = true;
      }

    for(i=0,k=10;i<2;i++)
      {
	n[i]=j / k;
	j = j % k;
	k = k/10;
      }
    n[2]=-1;

    while(n[0]==0 && n[1]!=-1)
      {
	for(i=0;i<2;i++)
	  {
	    n[i]=n[(i+1)];
	  }
      }/*end while loop*/
  }

  public void addValue(int a)
  {
    numvalue+=a;
    setValue(numvalue);
  }

  public void drawMe(Graphics g)
  {
    int tx=x;
    int i;

    for(i=0;i<2 && n[i]!=-1 ;i++)
      {
	if(!redNumber)
	  g.drawImage(wad.gn[n[i]],tx,y,null);
	else
	  g.drawImage(wad.rn[n[i]],tx,y,null);
	
	tx+=w[n[i]];
      }

  }

}

/**********************************************************************/
/*                             Class glevel                           */
/**********************************************************************/

final class glevel
{

  double t1,t2,t3;
  double popupprob;
  int topdelay;
  int ystep;

  WhackaDebtor w;

  public glevel(WhackaDebtor wad)
  {

    w=wad;  

    t1 = 0.4;
    t2 = 0.7;
    t3 = 0.9;
    popupprob = 0.7;
    topdelay = 100;
    ystep = 10;
  }

  public void setLevel(int l)
  {
    switch (l)
      {
      case 0:
	t1 = 0.5;
	t2 = 0.75;
	t3 = 0.9;
	popupprob = 0.7;
	topdelay = 120;
	ystep = 5;
	break;

      case 1:
	t1 = 0.5;
	t2 = 0.75;
	t3 = 0.9;
	popupprob = 0.7;
	topdelay = 115;
	ystep = 6;
	break;

      case 2:
	t1 = 0.45;
	t2 = 0.75;
	t3 = 0.9;
	popupprob = 0.7;
	topdelay = 110;
	ystep = 7;
	break;

      case 3:
	t1 = 0.4;
	t2 = 0.7;
	t3 = 0.9;
	popupprob = 0.7;
	topdelay = 100;
	ystep = 10;
	break;

      case 4:
	t1 = 0.3;
	t2 = 0.65;
	t3 = 0.85;
	popupprob = 0.7;
	topdelay = 95;
	ystep = 11;
	break;

      case 5:
	t1 = 0.2;
	t2 = 0.6;
	t3 = 0.83;
	popupprob = 0.7;
	topdelay = 90;
	ystep = 12;
	break;

      case 6:
	t1 = 0.15;
	t2 = 0.55;
	t3 = 0.81;
	popupprob = 0.71;
	topdelay = 85;
	ystep = 13;
	break;

      case 7:
	t1 = 0.1;
	t2 = 0.5;
	t3 = 0.8;
	popupprob = 0.72;
	topdelay = 80;
	ystep = 14;
	break;

      case 8:
	t1 = 0.05;
	t2 = 0.45;
	t3 = 0.87;
	popupprob = 0.73;
	topdelay = 75;
	ystep = 15;
	break;

      case 9:
	t1 = 0.03;
	t2 = 0.4;
	t3 = 0.84;
	popupprob = 0.74;
	topdelay = 70;
	ystep = 16;
	break;

      case 10:
	t1 = 0.04;
	t2 = 0.35;
	t3 = 0.7;
	popupprob = 0.75;
	topdelay = 65;
	ystep = 17;
	break;

      case 11:
	t1 = 0.03;
	t2 = 0.3;
	t3 = 0.67;
	popupprob = 0.8;
	topdelay = 60;
	ystep = 18;
	break;

      case 12:
	t1 = 0.04;
	t2 = 0.25;
	t3 = 0.65;
	popupprob = 0.81;
	topdelay = 55;
	ystep = 19;
	break;

      case 13:
	t1 = 0.05;
	t2 = 0.2;
	t3 = 0.63;
	popupprob = 0.82;
	topdelay = 50;
	ystep = 20;
	break;

      case 14:
	t1 = 0.05;
	t2 = 0.15;
	t3 = 0.6;
	popupprob = 0.83;
	topdelay = 45;
	ystep = 21;
	break;

      case 15:
	t1 = 0.05;
	t2 = 0.1;
	t3 = 0.57;
	popupprob = 0.84;
	topdelay = 40;
	ystep = 22;
	break;

      case 16:
	t1 = 0.03;
	t2 = 0.05;
	t3 = 0.53;
	popupprob = 0.85;
	topdelay = 35;
	ystep = 23;
	break;

      case 17:
	t1 = 0.04;
	t2 = 0.05;
	t3 = 0.5;
	popupprob = 0.85;
	topdelay = 30;
	ystep = 24;
	break;

      case 18:
	t1 = 0.01;
	t2 = 0.02;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 25;
	ystep = 25;
	break;

      case 19:
	t1 = 0.01;
	t2 = 0.02;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 24;
	ystep = 26;
	break;

      case 20:
	t1 = 0.01;
	t2 = 0.02;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 23;
	ystep = 27;
	break;

      case 21:
	t1 = 0.01;
	t2 = 0.02;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 22;
	ystep = 28;
	break;

      case 22:
	t1 = 0.01;
	t2 = 0.02;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 21;
	ystep = 29;
	break;

      case 23:
	t1 = 0.01;
	t2 = 0.02;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 20;
	ystep = 30;
	break;

      case 24:
	t1 = 0.01;
	t2 = 0.02;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 19;
	ystep = 31;
	break;

		default:
      case 25:
	t1 = 0.01;
	t2 = 0.02;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 18;
	ystep = 32;
	break;

			/*
      case 26:
	t1 = 0.0;
	t2 = 0.0;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 17;
	ystep = 33;
	break;

      case 27:
	t1 = 0.0;
	t2 = 0.0;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 16;
	ystep = 34;
	break;

      case 28:
	t1 = 0.0;
	t2 = 0.0;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 15;
	ystep = 35;
	break;

      case 29:
	t1 = 0.0;
	t2 = 0.0;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 14;
	ystep = 36;
	break;

      case 30:
	t1 = 0.0;
	t2 = 0.0;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 13;
	ystep = 37;
	break;

      case 31:
	t1 = 0.0;
	t2 = 0.0;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 12;
	ystep = 38;
	break;

      case 32:
	t1 = 0.0;
	t2 = 0.0;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 11;
	ystep = 39;
	break;

      case 33:
	t1 = 0.0;
	t2 = 0.0;
	t3 = 0.45;
	popupprob = 0.85;
	topdelay = 10;
	ystep = 40;
	break;
*/
      }/*end switch*/

    for(int i=0;i<6;i++)
      {
	w.d[i].t1=t1;
	w.d[i].t2=t2;
	w.d[i].t3=t3;
	w.d[i].popupprob=popupprob;
	w.d[i].topdelay=topdelay;
	w.d[i].ystep=ystep;
	w.d[i].p=0;
	w.d[i].reachedTop = false;
      }

  }
  

}

/**********************************************************************/
/*                             Class debtor                           */
/**********************************************************************/

final class debtor
{

  int x;
  int y;

  int sx,sy;
  
  int t;/*debtor type 0-green, 1-blue, 2-grey, 3-lawer*/

  int p;
  int counter;

  double t1,t2,t3;

  int eyedelay=3;
  int eyepos=0;

  int topdelay=100;

  int facecount=0;
  int facepic = 0;

  int movey;
  int ystep = 10;

  public boolean reachedTop=false;

  double popupprob;

  WhackaDebtor w;

  public debtor(WhackaDebtor wad,int x,int y)
  {
    this.x=x;
    this.y=y;

    popupprob = 0.7;

    sx = 0;
    sy = 0;

    ystep = 10;

    t1=0.40;
    t2=0.70;
    t3=0.90;

    t=0;
    p=0;
    counter = 0;
    w=wad;
  }

  void testHit()
  {

    if(!w.hit)
      return;
    int b = y - movey;
    sy=b;
    if(w.hitx>=(x-10) && w.hitx<(x+43+10))
      {
	if(w.hity>=(b-10) && w.hity<(b+36+10))
	  {
	    p=8;
	    reachedTop=false;
	    if(t!=3)
	      {
		w.totaldebtor++;
	        w.totaldebtorhit++;
	      }
	    counter = 50;
	    w.hit=false;

	    if (w.SOUND&&w.PLAYSOUNDS)
	      {
		w.slap.play();
	      }

	    switch(t)
	      {
	      case 0:
		w.score+=15;
		sx = x+23;
		break;
	      case 1:
		w.score+=25;
		sx = x-3;
		break;
	      case 2:
		w.score+=40;
		sx = x+8;
		break;
	      case 3:
		w.score-=100;
		sx = x+2;
		break;
	      }/*scoring switch*/
	  }
      }

    return;
  }

  public void move()
  {
    switch (p)
      {
      case 0:
	if(reachedTop)
	  {
	    w.totaldebtor++;
	    reachedTop=false;
	  }
	p=1;
	counter = (int)(w.rand.nextDouble()*100+50);
	break;
      case 1:
	counter--;
	if(counter<1)
	  {
	    p=2;
	    /*counter=(int)(w.rand.nextDouble()*40+20);*/
	    counter = 7;
	    eyepos=(int)(w.rand.nextDouble()*3);
	    eyedelay = (int)(w.rand.nextDouble()*5+2);
	  }/*end pick new p*/
	break;

      case 2:
      case 4:
      case 3:
	eyedelay--;
	if(eyedelay<1)
	  {
	    eyepos=(int)(w.rand.nextDouble()*3);
	    eyedelay = (int)(w.rand.nextDouble()*10+4);
	  }
	counter--;
	if(counter<1)
	  {
	    /*randomally choose next phase*/

	    if(p==2)
	      {
		p=3;
		counter=(int)(w.rand.nextDouble()*40+20);
	      }
	    else
	      if(p==3)
		{
		  if(w.rand.nextDouble()>popupprob)
		    {
		      p=4;
		      counter = 7;
		    }
		  else
		    {
		      movey=14;

		      double r = w.rand.nextDouble();
		      if(r < t1)
			t=0;
		      else
			if(r < t2)
			  t=1;
			else
			  if(r < t3)
			    t=2;
			  else
			    t=3;

		      if(t==3||w.rand.nextDouble()>0.15)
			p=5;
		      else
			p=9;

		    }
		}
	      else
		{
		  p=0;
		}
	    
	  }/*end pick new p*/
	break; 

      case 5: /*move up*/
	testHit();

	facecount++;
	if(facecount>2)
	  {
	    facecount = 0;
	    if(facepic==1)
	      facepic=0;
	    else
	      facepic=1;
	  }
	movey+=ystep;
	if(movey>80)
	  {
	    movey=80;
	    p=6;
	    if(t!=3)
	      reachedTop = true;
	    counter = topdelay;
	  }
	break;
      case 6: /*hang at top*/
	testHit();
	facecount++;
	if(facecount>2)
	  {
	    facecount = 0;
	    if(facepic==1)
	      facepic=0;
	    else
	      facepic=1;
	  }
	counter--;
	if(counter<1)
	  {
	    p=7;
	  }
	break;
      case 7: /*move down*/
	facecount++;
	if(facecount>2)
	  {
	    facecount = 0;
	    if(facepic==1)
	      facepic=0;
	    else
	      facepic=1;
	  }
	movey-=ystep;
	if(movey<0)
	  {
	    p=0;
	    movey=0;
	  }
	if(movey>=14)
	  testHit();
	break;

      case 8: /*Dead*/
	counter--;
	if(counter == 35&&w.SOUND&&w.PLAYSOUNDS)
	  {
	    switch(t)
	      {
	      case 3:
		w.handsoff.play();
		break;
	      default:
		w.cashring.play();
		break;
	      }
	  }
	if(counter<1)
	  {
	    p=0;
	  }
	break;

      case 9: /*Fake move. :)*/
	testHit();
	facecount++;
	if(facecount>2)
	  {
	    facecount = 0;
	    if(facepic==1)
	      facepic=0;
	    else
	      facepic=1;
	  }
	movey+=ystep;
	if(movey>80)
	  {
	    movey=80;
	    p=7;
	  }
	break;

      default :
	p = 0;
	break;
      }/*end p switch*/
  }

  public void drawMe(Graphics g)
  {
    switch(p)
      {
      case 3:
	g.drawImage(w.pk[eyepos],x,y-14,null);
	break;
      case 2:
	g.drawImage(w.pk[eyepos],x,y-14+counter*2,null);
	break;
      case 4:
	g.drawImage(w.pk[eyepos],x,y-counter*2,null);
	break;

      case 5:
      case 6:
      case 7:
      case 9:
	switch(t)
	  {
	  case 0:
	    g.drawImage(w.gp[facepic],x,y-movey,null);
	    break;
	  case 1:
	    g.drawImage(w.bp[facepic],x,y-movey,null);
	    break;
	  case 2:
	    g.drawImage(w.rp[facepic],x,y-movey,null);
	    break;
	  case 3:
	    g.drawImage(w.lp[facepic],x,y-movey,null);
	    break;
	  }/*end switch*/
	break;

      case 8:
	switch(t)
	  {
	  case 0:
	    g.drawImage(w.gp[2],x+3,y-movey+3,null);
	    g.drawImage(w.sc[0],sx,sy-50+counter,null);
	    break;
	  case 1:
	    g.drawImage(w.bp[2],x-7,y-movey+1,null);
	    g.drawImage(w.sc[1],sx,sy-50+counter,null);
	    break;
	  case 2:
	    g.drawImage(w.rp[2],x-6,y-movey+4,null);
	    g.drawImage(w.sc[2],sx,sy-50+counter,null);
	    break;
	  case 3:
	    g.drawImage(w.lp[2],x,y-movey,null);
	    g.drawImage(w.sc[3],sx,sy-50+counter,null);
	    break;
	  }/*end switch*/
	break;

      default:

	break;
      }
  }

  public void setRange(double a, double b, double c)
  {
    t1 = a;
    t2 = b;
    t3 = c;
  }

}

/**********************************************************************/
/*                          Class WhackaDebtor                        */
/**********************************************************************/

public final class WhackaDebtor extends Applet implements Runnable,MouseListener, MouseMotionListener, KeyListener
{

  mallet m;
  neon neonFace;
  
  public debtor[] d;
  public static Image[] bp;
  public static Image[] bx;
  public static Image[] gn;
  public static Image[] gp;
  public static Image[] rp;
  public static Image[] lp;
  public static Image[] mt;
  public static Image[] nf;
  public static Image[] rn;
  public static Image sb;
  public static Image[] pk;
  
  public static Image[] sc; 

  public static Image[] clouds;
  public static Image[] bosses;
  public static Image[] hands;
  public static Image[] sayings;

  public static Image victorysign = null;

  public static Image db;
  public static Image backbuff;
  public static Image backbuff2;

  public static Image totb;
  public static Image hitb;

  public static Image levelb;

  public static Image spacebar;
  public static Image spacebarborder;
  
  public static Image[] textinfo;

  public boolean hit=false;
  public int hitx=0;
  public int hity=0;

  public int score;
  public gnumber scoreBoard;
  
  public static Random rand;

  public int sleeptime=10;

  public int totaldebtor;
  public int totaldebtorhit;

  int totalDebtor;
  int totalDebtorHit;

  public boolean THREADISALIVE;

  public gnumber2 totalBoard;
  public gnumber2 hitBoard;

  public gnumber3 levelBoard;

  int gameLevel = 0;
  int levelLimit = 10;

  boolean breakAnimatedLoop = false;
  
  glevel levelSet;

  public static AudioClip happytheme = null;
  public static AudioClip slap = null;
  public static AudioClip cashring = null;
  public static AudioClip handsoff = null;
  public static AudioClip wohoo = null;
  public static AudioClip yesohyes = null;

  boolean SOUND = true;
  boolean PLAYSOUNDS = true;

  public void start()
  {
    
    THREADISALIVE = true;

    try
      {
	happytheme = getAudioClip(getDocumentBase(),"audio/happytheme.au");
	slap = getAudioClip(getDocumentBase(),"audio/slap.au");
	cashring = getAudioClip(getDocumentBase(),"audio/cashring.au");
	handsoff = getAudioClip(getDocumentBase(),"audio/handsoff.au");
	wohoo = getAudioClip(getDocumentBase(),"audio/wohoo.au");
	yesohyes = getAudioClip(getDocumentBase(),"audio/yesohyes.au");
      }
    catch(Exception e)
      {
	System.out.println("Sounds unavailible");
	SOUND = false;
      }

    try
      {
	victorysign = getImage(getCodeBase(),"wd_images/victorysign.gif");

	textinfo[0] = getImage(getCodeBase(),"wd_images/TextPic.gif");
		/*
	textinfo[0] = getImage(getCodeBase(),"wd_images/TEXT1.gif");
	textinfo[1] = getImage(getCodeBase(),"wd_images/TEXT2.gif");
	textinfo[2] = getImage(getCodeBase(),"wd_images/TEXT3.gif");
	textinfo[3] = getImage(getCodeBase(),"wd_images/TEXT4.gif");
	textinfo[4] = getImage(getCodeBase(),"wd_images/TEXT5.gif");
	textinfo[5] = getImage(getCodeBase(),"wd_images/TEXT6.gif");
*/

	spacebar = getImage(getCodeBase(),"wd_images/spacebar.gif");
	spacebarborder = getImage(getCodeBase(),"wd_images/spacebarborder.gif");

	clouds[0] = getImage(getCodeBase(),"wd_images/cloud1.gif");
	clouds[1] = getImage(getCodeBase(),"wd_images/cloud2.gif");
	clouds[2] = getImage(getCodeBase(),"wd_images/cloud3.gif");
	clouds[3] = getImage(getCodeBase(),"wd_images/cloud4.gif");

	bosses[0] = getImage(getCodeBase(),"wd_images/boss1.gif");
	bosses[1] = getImage(getCodeBase(),"wd_images/boss2.gif");
	bosses[2] = getImage(getCodeBase(),"wd_images/boss3.gif");
	bosses[3] = getImage(getCodeBase(),"wd_images/boss4.gif");

	hands[0] = getImage(getCodeBase(),"wd_images/theaxe1.gif");
	hands[1] = getImage(getCodeBase(),"wd_images/theaxe2.gif");
	hands[2] = getImage(getCodeBase(),"wd_images/theaxe3.gif");

	hands[3] = getImage(getCodeBase(),"wd_images/vhand1.gif");
	hands[4] = getImage(getCodeBase(),"wd_images/vhand2.gif");
	hands[5] = getImage(getCodeBase(),"wd_images/vhand3.gif");

	hands[6] = getImage(getCodeBase(),"wd_images/thumbsup.gif");
	hands[7] = getImage(getCodeBase(),"wd_images/thumbsdown.gif");

	sayings[0] = getImage(getCodeBase(),"wd_images/bosssign1.gif");
	sayings[1] = getImage(getCodeBase(),"wd_images/bosssign2.gif");
	sayings[2] = getImage(getCodeBase(),"wd_images/bosssign3.gif");
	sayings[3] = getImage(getCodeBase(),"wd_images/bosssign4.gif");

	levelb = getImage(getCodeBase(),"wd_images/wd_level.gif");

	totb = getImage(getCodeBase(),"wd_images/total.gif");
	hitb = getImage(getCodeBase(),"wd_images/hit.gif");

	bp[0]= getImage(getCodeBase(),"wd_images/blue1.gif");
	bp[1]= getImage(getCodeBase(),"wd_images/blue2.gif");
	bp[2]= getImage(getCodeBase(),"wd_images/blue3.gif");
	bx[0]= getImage(getCodeBase(),"wd_images/Box1.gif");
	bx[1]= getImage(getCodeBase(),"wd_images/Box2.gif");
	bx[2]= getImage(getCodeBase(),"wd_images/Box3.gif");
	gn[0]= getImage(getCodeBase(),"wd_images/G0.gif");
	gn[1]= getImage(getCodeBase(),"wd_images/G1.gif");
	gn[2]= getImage(getCodeBase(),"wd_images/G2.gif");
	gn[3]= getImage(getCodeBase(),"wd_images/G3.gif");
	gn[4]= getImage(getCodeBase(),"wd_images/G4.gif");
	gn[5]= getImage(getCodeBase(),"wd_images/G5.gif");
	gn[6]= getImage(getCodeBase(),"wd_images/G6.gif");
	gn[7]= getImage(getCodeBase(),"wd_images/G7.gif");
	gn[8]= getImage(getCodeBase(),"wd_images/G8.gif");
	gn[9]= getImage(getCodeBase(),"wd_images/G9.gif");
	gp[0]= getImage(getCodeBase(),"wd_images/green1.gif");
	gp[1]= getImage(getCodeBase(),"wd_images/green2.gif");
	gp[2]= getImage(getCodeBase(),"wd_images/green3.gif");
	rp[0]= getImage(getCodeBase(),"wd_images/grey1.gif");
	rp[1]= getImage(getCodeBase(),"wd_images/grey2.gif");
	rp[2]= getImage(getCodeBase(),"wd_images/grey3.gif");
	lp[0]= getImage(getCodeBase(),"wd_images/lawyer1.gif");
	lp[1]= getImage(getCodeBase(),"wd_images/lawyer2.gif");
	lp[2]= getImage(getCodeBase(),"wd_images/lawyer3.gif");
	mt[0]= getImage(getCodeBase(),"wd_images/mallet-a.gif");
	mt[1]= getImage(getCodeBase(),"wd_images/mallet-b.gif");
	mt[2]= getImage(getCodeBase(),"wd_images/mallet-c.gif");
	mt[3]= getImage(getCodeBase(),"wd_images/mallet-d.gif");
	mt[4]= getImage(getCodeBase(),"wd_images/mallet-e.gif");
	mt[5]= getImage(getCodeBase(),"wd_images/mallet-f.gif");
	nf[0]= getImage(getCodeBase(),"wd_images/NeonFace1.gif");
	nf[1]= getImage(getCodeBase(),"wd_images/NeonFace2.gif");
	rn[0]= getImage(getCodeBase(),"wd_images/R0.gif");
	rn[1]= getImage(getCodeBase(),"wd_images/R1.gif");
	rn[2]= getImage(getCodeBase(),"wd_images/R2.gif");
	rn[3]= getImage(getCodeBase(),"wd_images/R3.gif");
	rn[4]= getImage(getCodeBase(),"wd_images/R4.gif");
	rn[5]= getImage(getCodeBase(),"wd_images/R5.gif");
	rn[6]= getImage(getCodeBase(),"wd_images/R6.gif");
	rn[7]= getImage(getCodeBase(),"wd_images/R7.gif");
	rn[8]= getImage(getCodeBase(),"wd_images/R8.gif");
	rn[9]= getImage(getCodeBase(),"wd_images/R9.gif");
	sb   = getImage(getCodeBase(),"wd_images/ScoreBoard.gif");
	pk[0]= getImage(getCodeBase(),"wd_images/peek-left.gif");
	pk[1]= getImage(getCodeBase(),"wd_images/peek-right.gif");
	pk[2]= getImage(getCodeBase(),"wd_images/peek-up.gif");

	sc[0]= getImage(getCodeBase(),"wd_images/p15.gif");
	sc[1]= getImage(getCodeBase(),"wd_images/p25.gif");
	sc[2]= getImage(getCodeBase(),"wd_images/p40.gif");
	sc[3]= getImage(getCodeBase(),"wd_images/m100.gif");
      }
    catch(Exception e)
      {
	System.out.println("Exception "+e.getMessage());
	Toolkit t = Toolkit.getDefaultToolkit();

	victorysign = t.getImage("wd_images/victorysign.gif");

	textinfo[0] = t.getImage("wd_images/TextPic.gif");
		/*
	textinfo[0] = t.getImage("wd_images/TEXT1.gif");
	textinfo[1] = t.getImage("wd_images/TEXT2.gif");
	textinfo[2] = t.getImage("wd_images/TEXT3.gif");
	textinfo[3] = t.getImage("wd_images/TEXT4.gif");
	textinfo[4] = t.getImage("wd_images/TEXT5.gif");
	textinfo[5] = t.getImage("wd_images/TEXT6.gif");
*/

	spacebar = t.getImage("wd_images/spacebar.gif");
	spacebarborder = t.getImage("wd_images/spacebarborder.gif");

	clouds[0] = t.getImage("wd_images/cloud1.gif");
	clouds[1] = t.getImage("wd_images/cloud2.gif");
	clouds[2] = t.getImage("wd_images/cloud3.gif");
	clouds[3] = t.getImage("wd_images/cloud4.gif");

	bosses[0] = t.getImage("wd_images/boss1.gif");
	bosses[1] = t.getImage("wd_images/boss2.gif");
	bosses[2] = t.getImage("wd_images/boss3.gif");
	bosses[3] = t.getImage("wd_images/boss4.gif");

	hands[0] = t.getImage("wd_images/theaxe1.gif");
	hands[1] = t.getImage("wd_images/theaxe2.gif");
	hands[2] = t.getImage("wd_images/theaxe3.gif");

	hands[3] = t.getImage("wd_images/vhand1.gif");
	hands[4] = t.getImage("wd_images/vhand2.gif");
	hands[5] = t.getImage("wd_images/vhand3.gif");

	hands[6] = t.getImage("wd_images/thumbsup.gif");
	hands[7] = t.getImage("wd_images/thumbsdown.gif");

	sayings[0] = t.getImage("wd_images/bosssign1.gif");
	sayings[1] = t.getImage("wd_images/bosssign2.gif");
	sayings[2] = t.getImage("wd_images/bosssign3.gif");
	sayings[3] = t.getImage("wd_images/bosssign4.gif");

	levelb = t.getImage("wd_images/wd_level.gif");

	totb = t.getImage("wd_images/total.gif");
	hitb = t.getImage("wd_images/hit.gif");

	bp[0]= t.getImage("wd_images/blue1.gif");
	bp[1]= t.getImage("wd_images/blue2.gif");
	bp[2]= t.getImage("wd_images/blue3.gif");
	bx[0]= t.getImage("wd_images/Box1.gif");
	bx[1]= t.getImage("wd_images/Box2.gif");
	bx[2]= t.getImage("wd_images/Box3.gif");
	gn[0]= t.getImage("wd_images/G0.gif");
	gn[1]= t.getImage("wd_images/G1.gif");
	gn[2]= t.getImage("wd_images/G2.gif");
	gn[3]= t.getImage("wd_images/G3.gif");
	gn[4]= t.getImage("wd_images/G4.gif");
	gn[5]= t.getImage("wd_images/G5.gif");
	gn[6]= t.getImage("wd_images/G6.gif");
	gn[7]= t.getImage("wd_images/G7.gif");
	gn[8]= t.getImage("wd_images/G8.gif");
	gn[9]= t.getImage("wd_images/G9.gif");
	gp[0]= t.getImage("wd_images/green1.gif");
	gp[1]= t.getImage("wd_images/green2.gif");
	gp[2]= t.getImage("wd_images/green3.gif");
	rp[0]= t.getImage("wd_images/grey1.gif");
	rp[1]= t.getImage("wd_images/grey2.gif");
	rp[2]= t.getImage("wd_images/grey3.gif");
	lp[0]= t.getImage("wd_images/lawyer1.gif");
	lp[1]= t.getImage("wd_images/lawyer2.gif");
	lp[2]= t.getImage("wd_images/lawyer3.gif");
	mt[0]= t.getImage("wd_images/mallet-a.gif");
	mt[1]= t.getImage("wd_images/mallet-b.gif");
	mt[2]= t.getImage("wd_images/mallet-c.gif");
	mt[3]= t.getImage("wd_images/mallet-d.gif");
	mt[4]= t.getImage("wd_images/mallet-e.gif");
	mt[5]= t.getImage("wd_images/mallet-f.gif");
	nf[0]= t.getImage("wd_images/NeonFace1.gif");
	nf[1]= t.getImage("wd_images/NeonFace2.gif");
	rn[0]= t.getImage("wd_images/R0.gif");
	rn[1]= t.getImage("wd_images/R1.gif");
	rn[2]= t.getImage("wd_images/R2.gif");
	rn[3]= t.getImage("wd_images/R3.gif");
	rn[4]= t.getImage("wd_images/R4.gif");
	rn[5]= t.getImage("wd_images/R5.gif");
	rn[6]= t.getImage("wd_images/R6.gif");
	rn[7]= t.getImage("wd_images/R7.gif");
	rn[8]= t.getImage("wd_images/R8.gif");
	rn[9]= t.getImage("wd_images/R9.gif");
	sb   = t.getImage("wd_images/ScoreBoard.gif");
	pk[0]= t.getImage("wd_images/peek-left.gif");
	pk[1]= t.getImage("wd_images/peek-right.gif");
	pk[2]= t.getImage("wd_images/peek-up.gif");

	sc[0]= t.getImage("wd_images/p15.gif");
	sc[1]= t.getImage("wd_images/p25.gif");
	sc[2]= t.getImage("wd_images/p40.gif");
	sc[3]= t.getImage("wd_images/m100.gif");
      }

    MediaTracker MT;
    MT = new MediaTracker(this);

    MT.addImage(victorysign,0);

	  
    MT.addImage(textinfo[0],0);
	  /*
    MT.addImage(textinfo[1],0);
    MT.addImage(textinfo[2],0);
    MT.addImage(textinfo[3],0);
    MT.addImage(textinfo[4],0);
    MT.addImage(textinfo[5],0);
*/

    MT.addImage(spacebar,0);
    MT.addImage(spacebarborder,0);

    MT.addImage(bosses[0],0);
    MT.addImage(bosses[1],0);
    MT.addImage(bosses[2],0);
    MT.addImage(bosses[3],0);
    MT.addImage(hands[0],0);
    MT.addImage(hands[1],0);
    MT.addImage(hands[2],0);
    MT.addImage(hands[3],0);
    MT.addImage(hands[4],0);
    MT.addImage(hands[5],0);
    MT.addImage(hands[6],0);
    MT.addImage(hands[7],0);
    MT.addImage(clouds[0],0);
    MT.addImage(clouds[1],0);
    MT.addImage(clouds[2],0);
    MT.addImage(clouds[3],0);
    MT.addImage(sayings[0],0);
    MT.addImage(sayings[1],0);
    MT.addImage(sayings[2],0);
    MT.addImage(sayings[3],0);

    MT.addImage(levelb,0);

    MT.addImage(totb,0);
    MT.addImage(hitb,0);
    
    MT.addImage(bp[0],0);
    MT.addImage(bp[1],0);
    MT.addImage(bp[2],0);

    MT.addImage(bx[0],0);
    MT.addImage(bx[1],0);
    MT.addImage(bx[2],0);

    MT.addImage(gn[0],0);
    MT.addImage(gn[1],0);
    MT.addImage(gn[2],0);
    MT.addImage(gn[3],0);
    MT.addImage(gn[4],0);
    MT.addImage(gn[5],0);
    MT.addImage(gn[6],0);
    MT.addImage(gn[7],0);
    MT.addImage(gn[8],0);
    MT.addImage(gn[9],0);

    MT.addImage(gp[0],0);
    MT.addImage(gp[1],0);
    MT.addImage(gp[2],0);

    MT.addImage(rp[0],0);
    MT.addImage(rp[1],0);
    MT.addImage(rp[2],0);

    MT.addImage(lp[0],0);
    MT.addImage(lp[1],0);
    MT.addImage(lp[2],0);

    MT.addImage(mt[0],0);
    MT.addImage(mt[1],0);
    MT.addImage(mt[2],0);
    MT.addImage(mt[3],0);
    MT.addImage(mt[4],0);
    MT.addImage(mt[5],0);

    MT.addImage(nf[0],0);
    MT.addImage(nf[1],0);

    MT.addImage(rn[0],0);
    MT.addImage(rn[1],0);
    MT.addImage(rn[2],0);
    MT.addImage(rn[3],0);
    MT.addImage(rn[4],0);
    MT.addImage(rn[5],0);
    MT.addImage(rn[6],0);
    MT.addImage(rn[7],0);
    MT.addImage(rn[8],0);
    MT.addImage(rn[9],0);

    MT.addImage(sb,0);

    MT.addImage(pk[0],0);
    MT.addImage(pk[1],0);
    MT.addImage(pk[2],0);

    MT.addImage(sc[0],0);
    MT.addImage(sc[1],0);
    MT.addImage(sc[2],0);
    MT.addImage(sc[3],0);

    try{MT.waitForAll();}
    catch(Exception e){}

    Thread t;
    t=new Thread(this); 
    t.start();


  }
  
  public void init()
  {
    happytheme = null;

    rand = new Random();

    bp = new Image[3];  //Blue people
    bx = new Image[3];  //Box parts
    gn = new Image[10]; //Green numbers
    gp = new Image[3];  //Green people
    rp = new Image[3];  //Grey people
    lp = new Image[3];  //Lawyers
    mt = new Image[6];  //Mallets
    nf = new Image[2];  //Neon Faces
    rn = new Image[10]; //Red Numbers
    sb = null;          //Scoreboard

    totb = null;
    hitb = null;

    levelb = null;

    textinfo = new Image[1];

    pk = new Image[3];  //Peeking

    sc = new Image[4];  //Scores

    bosses = new Image[4];  //Boss Heads
    sayings = new Image[4]; //Boss Signs
    hands = new Image[8];   //Boss Hands
    clouds = new Image[4];  //Dark Boss Cloud

    spacebar = null;
    spacebarborder = null;

    db =createImage(600,450);
    backbuff = createImage(600,450);
    backbuff2 = createImage(600,450);

    d = new debtor[6];
    /*Back row*/
    d[0]=new debtor(this,129,287);
    d[1]=new debtor(this,232,287);
    d[2]=new debtor(this,335,287);
    /*Front row*/
    d[3]=new debtor(this,174,341);
    d[4]=new debtor(this,283,341);
    d[5]=new debtor(this,392,341);

    m = new mallet(this);
    neonFace = new neon(this);

    score = 0;
    scoreBoard = new gnumber(this);

    totaldebtor = 0;
    totaldebtorhit = 0;
    totalDebtor = 0;
    totalDebtorHit = 0;
    
    totalBoard = new gnumber2(this,519,98,true);
    hitBoard = new gnumber2(this,519,163,false);

    levelBoard = new gnumber3(this,538,228);

    levelSet = new glevel(this);

    levelSet.setLevel(0);

    addMouseMotionListener(this);
    addMouseListener(this);

    addKeyListener(this);

    PLAYSOUNDS = false;

  }

  public void update(Graphics g)
  {
    paint(g);
  }

  public void fastpaint()
  {
    Graphics g = getGraphics();
    if(g!=null)
      g.drawImage(db,0,0,null);
  }

  public void paint(Graphics g)
  {
    g.drawImage(db,0,0,null);
  }

  /*KeyListener functions*/
  public void keyPressed(KeyEvent e)
  {
  }
  
  public void keyReleased(KeyEvent e)
  {
    switch(e.getKeyChar())
      {
      case '1':
	sleeptime=100;
	break;
      case '2':
	sleeptime=85;
	break;
      case '3':
	sleeptime=70;
	break;
      case '4':
	sleeptime=60;
	break;
      case '5':
	sleeptime=50;
	break;
      case '6':
	sleeptime=40;
	break;
      case '7':
	sleeptime=30;
	break;
      case '8':
	sleeptime=20;
	break;
      case '9':
	sleeptime=10;
	break;
      case '0':
	sleeptime=5;
	break;

      }/*end switch*/
  }
  
  public void keyTyped(KeyEvent e)
  {

    switch(e.getKeyChar())
      {
      case '-':
      case '_':
	sleeptime--;
	if(sleeptime<5)
	  sleeptime=5;
	break;
	
      case '+':
      case '=':
	sleeptime++;
	break;

      case 'S':
      case 's':
	if(SOUND)
	  {
	    if(PLAYSOUNDS)
	      {
		PLAYSOUNDS = false;
		happytheme.stop();
	      }
	    else
	      {
		PLAYSOUNDS = true;
		happytheme.loop();
	      }
	  }
	break;

      case 'P':
      case 'p':
      case ' ':
	breakAnimatedLoop = true;
	break;
      }
    
  }
  
  /*MouseMotionListener functions*/
  public void mouseDragged(MouseEvent e)
  {
  }

  public void mouseMoved(MouseEvent e)
  {
    m.setC(e.getX(),e.getY());

    //System.out.println("Mouse moved to ("+e.getX()+","+e.getY()+")");
  }

  /*Mouselistener functions*/
  public void mouseReleased(MouseEvent e)
  {
  }

  public void mouseClicked(MouseEvent e)
  {
  }

  public void mouseEntered(MouseEvent e)
  {  
  }

  public void mouseExited(MouseEvent e)
  {   
  }

  public void mousePressed(MouseEvent e)
  {
    m.setT(e.getX(),e.getY());
    //System.out.println("WHACK! ("+e.getX()+","+e.getY()+")");  
  }

  public void uhavewon(Graphics g)
  {

    boolean playwohoo = true;

    breakAnimatedLoop = false;
    for(;!breakAnimatedLoop;)
      {
	for(int i=0;i<4&&!breakAnimatedLoop;i++)
	  {

	    neonFace.drawMe(g);
	    g.drawImage(clouds[3],21,8,null);

	    switch(i)
	      {
	      case 0:
		g.drawImage(hands[3],104,90,null);
		g.drawImage(bosses[0],168,28,null);
		break;
	      case 1:
	      case 3:
		g.drawImage(hands[4],106,91,null);
		g.drawImage(bosses[0],168,29,null);
		break;
	      default:
		g.drawImage(hands[5],108,90,null);
		g.drawImage(bosses[0],168,30,null);
		break;
	      }/*end switch*/

	    g.drawImage(victorysign,172,168,null);

	    scoreBoard.drawMe(g);
	    totalBoard.drawMe(g);
	    hitBoard.drawMe(g);
	    levelBoard.drawMe(g);

	    g.drawImage(spacebar,191,355,null);
	    g.drawImage(spacebarborder,186+(int)(rand.nextDouble()*5)-2,
			350+(int)(rand.nextDouble()*5)-2,null);

	    if(playwohoo&&SOUND&&PLAYSOUNDS)
	      yesohyes.play();

	    playwohoo = false;

	    /*repaint(0);*/
	    fastpaint();
	    try
	      {
		Thread.sleep(100);
	      }
	    catch(Exception e){}

	  }/*end vhand loop*/
      }/*outer animation loop*/
  }

  public void udogreat(Graphics g)
  {

    boolean playwohoo = true;

    breakAnimatedLoop = false;
    for(;THREADISALIVE&&!breakAnimatedLoop;)
      {
	for(int i=0;THREADISALIVE&&i<4&&!breakAnimatedLoop;i++)
	  {

	    neonFace.drawMe(g);
	    g.drawImage(clouds[3],21,8,null);

	    switch(i)
	      {
	      case 0:
		g.drawImage(hands[3],104,90,null);
		g.drawImage(bosses[0],168,28,null);
		break;
	      case 1:
	      case 3:
		g.drawImage(hands[4],106,91,null);
		g.drawImage(bosses[0],168,29,null);
		break;
	      default:
		g.drawImage(hands[5],108,90,null);
		g.drawImage(bosses[0],168,30,null);
		break;
	      }/*end switch*/

	    g.drawImage(sayings[0],186,151,null);

	    scoreBoard.drawMe(g);
	    totalBoard.drawMe(g);
	    hitBoard.drawMe(g);
	    levelBoard.drawMe(g);

	    g.drawImage(spacebar,191,355,null);
	    g.drawImage(spacebarborder,186+(int)(rand.nextDouble()*5)-2,
			350+(int)(rand.nextDouble()*5)-2,null);

	    if(playwohoo&&SOUND&&PLAYSOUNDS)
	      wohoo.play();

	    playwohoo = false;

	    /*repaint(0);*/
	    fastpaint();
	    try
	      {
		Thread.sleep(100);
	      }
	    catch(Exception e){}

	  }/*end vhand loop*/
      }/*outer animation loop*/
  }

  public void udogood(Graphics g)
  {
    breakAnimatedLoop = false;
    for(;THREADISALIVE&&!breakAnimatedLoop;)
      {
	for(int i=0;THREADISALIVE&&i<4&&!breakAnimatedLoop;i++)
	  {

	    neonFace.drawMe(g);
	    g.drawImage(clouds[3],21,8,null);

	    switch(i)
	      {
	      case 0:
		g.drawImage(hands[6],109,117,null);
		break;
	      case 1:
	      case 3:
		g.drawImage(hands[6],109,115,null);
		break;
	      default:
		g.drawImage(hands[6],109,113,null);
		break;
	      }/*end switch*/

	    g.drawImage(sayings[1],186,151,null);

	    g.drawImage(bosses[1],168,30,null);

	    scoreBoard.drawMe(g);
	    totalBoard.drawMe(g);
	    hitBoard.drawMe(g);
	    levelBoard.drawMe(g);

	    g.drawImage(spacebar,191,355,null);
	    g.drawImage(spacebarborder,186+(int)(rand.nextDouble()*5)-2,
			350+(int)(rand.nextDouble()*5)-2,null);

	    /*repaint(0);*/
	    fastpaint();
	    try
	      {
		Thread.sleep(100);
	      }
	    catch(Exception e){}

	  }/*end vhand loop*/
      }/*outer animation loop*/
  }

  public void udobad(Graphics g)
  {
    breakAnimatedLoop = false;
    for(;THREADISALIVE&&!breakAnimatedLoop;)
      {
	for(int i=0;THREADISALIVE&&i<4&&!breakAnimatedLoop;i++)
	  {

	    neonFace.drawMe(g);
	    g.drawImage(clouds[3],21,8,null);

	    switch(i)
	      {
	      case 0:
		g.drawImage(hands[7],116,141,null);
		break;
	      case 1:
	      case 3:
		g.drawImage(hands[7],116,139,null);
		break;
	      default:
		g.drawImage(hands[7],116,137,null);
		break;
	      }/*end switch*/

	    g.drawImage(sayings[2],186,151,null);

	    g.drawImage(bosses[2],168,30,null);

	    scoreBoard.drawMe(g);
	    totalBoard.drawMe(g);
	    hitBoard.drawMe(g);
	    levelBoard.drawMe(g);

	    g.drawImage(spacebar,191,355,null);
	    g.drawImage(spacebarborder,186+(int)(rand.nextDouble()*5)-2,
			350+(int)(rand.nextDouble()*5)-2,null);

	    /*repaint(0);*/
	    fastpaint();
	    try
	      {
		Thread.sleep(100);
	      }
	    catch(Exception e){}

	  }/*end vhand loop*/
      }/*outer animation loop*/
  }

  public void urfired(Graphics g)
  {
    breakAnimatedLoop = false;
    for(;THREADISALIVE&&!breakAnimatedLoop;)
      {
	for(int i=0;THREADISALIVE&&i<4&&!breakAnimatedLoop;i++)
	  {

	    neonFace.drawMe(g);
	    g.drawImage(clouds[3],21,8,null);

	    switch(i)
	      {
	      case 0:
		g.drawImage(hands[0],102,40,null);
		break;
	      case 1:
	      case 3:
		g.drawImage(hands[1],96,41,null);
		break;
	      default:
		g.drawImage(hands[2],87,43,null);
		break;
	      }/*end switch*/

	    g.drawImage(bosses[3],168+(int)(rand.nextDouble()*3)-1,
			30+(int)(rand.nextDouble()*3)-1,null);
	    g.drawImage(sayings[3],186,151,null);

	    scoreBoard.drawMe(g);
	    totalBoard.drawMe(g);
	    hitBoard.drawMe(g);
	    levelBoard.drawMe(g);

	    g.drawImage(spacebar,191,355,null);
	    g.drawImage(spacebarborder,186+(int)(rand.nextDouble()*5)-2,
			350+(int)(rand.nextDouble()*5)-2,null);

	    /*repaint(0);*/
	    fastpaint();
	    try
	      {
		Thread.sleep(100);
	      }
	    catch(Exception e){}

	  }/*end axe loop*/
      }/*outer animation loop*/
  }

  public void destroy()
  {

    THREADISALIVE = false;

  }

  /*Runnable interface*/
  public void run()
  {
    Graphics g=db.getGraphics();

    Graphics backg = backbuff.getGraphics();
    backg.setColor(Color.lightGray);
    backg.fillRect(0,0,600,450);
    backg.drawImage(sb,418,13,null);
    backg.drawImage(totb,497,82,null);
    backg.drawImage(hitb,497,146,null);
    backg.drawImage(levelb,528,212,null);
    backg.drawImage(nf[0],158,71,null);
    backg.drawImage(bx[0],92,265,null);
    backg.drawImage(bx[1],92,286,null);
    backg.drawImage(bx[2],92,340,null);

    Graphics backg2 = backbuff2.getGraphics();
    backg2.setColor(Color.lightGray);
    backg2.fillRect(0,0,600,450);
    backg2.drawImage(sb,418,13,null);
    backg2.drawImage(totb,497,82,null);
    backg2.drawImage(hitb,497,146,null);
    backg2.drawImage(levelb,528,212,null);
    backg2.drawImage(nf[1],158,71,null);
    backg2.drawImage(bx[0],92,265,null);
    backg2.drawImage(bx[1],92,286,null);
    backg2.drawImage(bx[2],92,340,null);

    boolean runGameLoop = true;

    int screwups;

    double hitratio;

    for(;THREADISALIVE;)
      {
	breakAnimatedLoop = false;
	for(;THREADISALIVE&&!breakAnimatedLoop;)
	  {
	    neonFace.drawMe(g);

		g.drawImage(textinfo[0],0,0,null);
		/*
	    g.drawImage(textinfo[0],115,17,null);
	    g.drawImage(textinfo[1],115,73,null);
	    g.drawImage(textinfo[2],115,147,null);
	    g.drawImage(textinfo[3],115,226,null);
	    g.drawImage(textinfo[4],45,259,null);
	    g.drawImage(textinfo[5],45,348,null);
	    */

	    /*repaint(0);*/
	    fastpaint();
	    try
	      {
		Thread.sleep(100);
	      }
	    catch(Exception e){}
	  }
	
	/*Re-Initialize game data*/
	runGameLoop = true;
	totaldebtor = 0;
	totaldebtorhit = 0;
	totalDebtor = 0;
	totalDebtorHit = 0;
	levelSet.setLevel(0);
	scoreBoard.setValue(0);
	totalBoard.setValue(0);
	hitBoard.setValue(0);
	levelBoard.setValue(0);
	screwups = 0;
	gameLevel = 0;
	
	if(SOUND&&PLAYSOUNDS)
	  {
	    happytheme.loop();
	  }
	
	for(;THREADISALIVE&&runGameLoop;)
	  {
	    
	    neonFace.move();
	    m.move();
	    
	    totaldebtor = 0;
	    totaldebtorhit = 0;
	    score = 0;
	    d[5].move();
	    d[4].move();
	    d[3].move();
	    d[2].move();
	    d[1].move();
	    d[0].move();
	    
	    hit = false;
	    
	    if(score !=0)
	      scoreBoard.addValue(score);
	    
	    if(totaldebtor !=0)
	      {
		totalBoard.addValue(totaldebtor);
		totalDebtor+=totaldebtor;
	      }
	    
	    if(totaldebtorhit !=0)
	      {
		hitBoard.addValue(totaldebtorhit);
		totalDebtorHit+=totaldebtorhit;
	      }
	    
	    neonFace.drawMe(g);
	    
	    d[0].drawMe(g);
	    d[1].drawMe(g);
	    d[2].drawMe(g);
	    
	    g.drawImage(bx[1],92,286,null);
	    /*draw front row here*/
	    
	    d[3].drawMe(g);
	    d[4].drawMe(g);
	    d[5].drawMe(g);
	    
	    g.drawImage(bx[2],92,340,null);
	    
	    scoreBoard.drawMe(g);
	    totalBoard.drawMe(g);
	    hitBoard.drawMe(g);
	    levelBoard.drawMe(g);
	    
	    m.drawMe(g);
	    
	    /*repaint(0);*/
	    fastpaint();
	    try
	      {
		Thread.sleep(sleeptime);
	      }
	    catch(Exception e){}
	    
	    if (totalDebtor>=levelLimit)
	      {
		
		boolean tmpgo=true;
		for(;THREADISALIVE&&tmpgo;)
		  {
		    
		    neonFace.move();
		    m.move();
		    
		    totaldebtor = 0;
		    totaldebtorhit = 0;
		    score = 0;
		    
		    tmpgo = false;
		    
		    for(int k=0;k<6;k++)
		      {    
			if(d[k].p!=0)
			  {
			    d[k].move();
			    tmpgo = true;
			  }
		      }/*end k loop*/
		    
		    hit = false;
		    
		    if(score !=0)
		      scoreBoard.addValue(score);
		    
		    if(totaldebtorhit !=0)
		      {
			hitBoard.addValue(totaldebtorhit);
			totalDebtorHit+=totaldebtorhit;
		      }
		    
		    neonFace.drawMe(g);
		    
		    d[0].drawMe(g);
		    d[1].drawMe(g);
		    d[2].drawMe(g);
		    
		    g.drawImage(bx[1],92,286,null);
		    /*draw front row here*/
		    
		    d[3].drawMe(g);
		    d[4].drawMe(g);
		    d[5].drawMe(g);
		    
		    g.drawImage(bx[2],92,340,null);
		    
		    scoreBoard.drawMe(g);
		    totalBoard.drawMe(g);
		    hitBoard.drawMe(g);
		    levelBoard.drawMe(g);
		    
		    m.drawMe(g);
		    
		    /*repaint(0);*/
		    fastpaint();
		    try
		      {
			Thread.sleep(sleeptime);
		      }
		    catch(Exception e){}
		    
		    
		  }/*tmpgo loop*/
		
		
		for(int i=0;i<7;i++)
		  {
		    neonFace.drawMe(g);
		    
		    switch(i)
		      {
		      case 0:
			g.drawImage(clouds[0],21,8,null);
			break;
		      case 1:
			g.drawImage(clouds[1],21,8,null);
			break;
		      case 2:
			g.drawImage(clouds[2],21,8,null);
			break;
		      case 3:
			g.drawImage(clouds[0],21,8,null);
			break;
		      case 4:
			g.drawImage(clouds[0],21,8,null);
			g.drawImage(clouds[1],21,8,null);
			break;
		      case 5:
			g.drawImage(clouds[0],21,8,null);
			g.drawImage(clouds[1],21,8,null);
			g.drawImage(clouds[2],21,8,null);
			break;
		      default :
			g.drawImage(clouds[3],21,8,null);
			break;
		      }
		    
		    scoreBoard.drawMe(g);
		    totalBoard.drawMe(g);
		    hitBoard.drawMe(g);
		    levelBoard.drawMe(g);
		    
		    /*repaint(0);*/
		    fastpaint();
		    try
		      {
			Thread.sleep(30);
		      }
		    catch(Exception e){}
		  }	    
		
		/*Judge how well the player did for the round and
		  display the appropriate animation.*/
		
		hitratio = (double)totalDebtorHit/(double)levelLimit;
		
		if(hitratio>=0.99)
		  {
		    screwups = 0;
		    udogreat(g);
		  }
		else
		  {
		    if(hitratio >= 0.59 && scoreBoard.redNumber==false)
		      {
			udogood(g);
			screwups = 0;
		      }
		    else
		      {
			screwups++;
			if(screwups<2)
			  {
			    udobad(g);
			  }
			else
			  {
			    urfired(g);
			    runGameLoop=false;
			  }
		      }
		  }
		
		totalDebtor = 0;
		totalDebtorHit = 0;
		totalBoard.setValue(0);
		hitBoard.setValue(0);
		gameLevel++;
		if(gameLevel<31)
		  {
		    levelBoard.setValue(gameLevel);
		    levelSet.setLevel(gameLevel);
		  }
		else
		  {
		    uhavewon(g);
		    runGameLoop=false;
		  }
		
		try
		  {
		    if(runGameLoop) Thread.sleep(2000);
		  }
		catch(Exception e){}
		
		m.p = 0;
	      }/*end if advance level*/
	    
	  }/*end main game loop*/
	
	if(SOUND&&PLAYSOUNDS)
	  {
	    happytheme.stop();
	  }
	
	try
	  {
	    Thread.sleep(2000);
	  }
	catch(Exception e){}

      }/*end Master for loop*/
    
  }/*end run method*/
  
}

