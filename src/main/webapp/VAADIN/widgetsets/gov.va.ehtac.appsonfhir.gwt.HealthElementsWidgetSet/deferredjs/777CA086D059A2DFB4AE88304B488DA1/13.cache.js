$wnd.gov_va_ehtac_appsonfhir_gwt_HealthElementsWidgetSet.runAsyncCallback13("function Irc(){}\nfunction Krc(){}\nfunction Mrc(){}\nfunction dbd(){GTb.call(this)}\nfunction nXb(a,b){return OE(a.H.vm(b),2)}\nfunction oXb(a){return a.e.q+'themes/'+a.P.b}\nfunction Ord(){iTb.call(this);this.I=B$d;this.b=new zCd}\nfunction sFc(c,a){var b=c;a.notifyChildrenOfSizeChange=BFd(function(){b.Ak()})}\nfunction AFc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction pFc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction oFc(a,b){var c,d;for(c=wzd(Sxd(a.g));c.b.Yf();){d=OE(Bzd(c),2);if(UE(a.g.vm(d))===UE(b)){return d}}return null}\nfunction tFc(a,b){var c,d;d=oFc(a,b);d!=null&&a.g.ym(d);c=OE(a.b.vm(b),246);if(c){a.b.ym(b);return Knb(a,c)}else if(b){return Knb(a,b)}return false}\nfunction qFc(a){var b,c,d;d=(Xib(),a.ac).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];Vib.Ue(c,bJd)}}\nfunction uFc(a,b){var c,d,e;if((el(),b).hasAttribute(uWd)){e=kl(b,uWd);a.f.xm(e,b);Rk(b,'')}else{d=(Xib(),hlb(b));for(c=0;c<d;c++){uFc(a,glb(b,c))}}}\nfunction vFc(a,b,c){var d,e;if(!b){return}d=PE(a.f.vm(c));if(!d&&a.e){throw new Jvd('No location '+c+pOd)}e=OE(a.g.vm(c),18);if(e==b){return}!!e&&tFc(a,e);a.e||(d=(Xib(),a.ac));Anb(a,b,(Xib(),d));a.g.xm(c,b)}\nfunction wFc(a,b){var c,d,e;d=b.Nf();e=OE(a.b.vm(d),246);if(p4b(b.xh())){if(!e){c=oFc(a,d);Knb(a,d);e=new v4b(b,a.c);znb(a,e,PE(a.f.vm(c)));a.b.xm(d,e)}k4b(e.b)}else{if(e){c=oFc(a,d);Knb(a,e);znb(a,d,PE(a.f.vm(c)));a.b.ym(d)}}}\nfunction Erc(c){var d={setter:function(a,b){a.d=b},getter:function(a){return a.d}};c.Vj(N9,x$d,d);var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.Vj(N9,y$d,d);var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.Vj(N9,z$d,d)}\nfunction xFc(){var a;Lnb.call(this);this.f=new zCd;this.g=new zCd;this.b=new zCd;this.j='';this.e=false;xmb(this,(Xib(),Xm($doc)));a=this.ac.style;Uo(a,PLd,(_o(),fHd));a[mOd]=0+(Ls(),qHd);a[HOd]=MJd;(KZb(),!JZb&&(JZb=new _Zb),KZb(),JZb).b.i&&Uo(a,MGd,(Nr(),XLd));Pk(this.ac,B$d);Tmb(this.ac,dVd,true)}\nfunction cbd(a){var b,c;if(a.b){return}c=(!a.G&&(a.G=wPb(a)),OE(OE(OE(a.G,5),38),163)).d;b=(!a.G&&(a.G=wPb(a)),OE(OE(OE(a.G,5),38),163)).c;if(c!=null){b=nXb(a.v,'layouts/'+c+'.html');b==null&&Rk(mmb(OE(cQb(a),77)),'<em>Layout file layouts/'+c+'.html is missing. Components will be drawn for debug purposes.<\\/em>')}b!=null&&rFc(OE(cQb(a),77),b,oXb(a.v));a.b=true}\nfunction nFc(a,b){var c,d,e,f,g,i,j,k;b=Twd(b,'_UID_',a.i+'__');a.j='';d=0;f=b.toLowerCase();i='';j=f.indexOf('<script',0);while(j>0){i+=Ywd(b,d,j);j=f.indexOf('>',j);e=f.indexOf('<\\/script>',j);a.j+=Ywd(b,j+1,e)+';';g=d=e+9;j=f.indexOf('<script',g)}i+=Xwd(b,d);f=i.toLowerCase();k=f.indexOf('<body');if(k<0){i=i}else{k=f.indexOf('>',k)+1;c=f.indexOf('<\\/body>',k);c>k?(i=Ywd(i,k,c)):(i=Xwd(i,k))}return i}\nfunction rFc(a,b,c){var d;b=nFc(a,b);d=d3b(c+'/layouts/');b=Twd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',A$d+d+'$3\"');b=Twd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',A$d+d+'$3\"');b=Twd(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');Rk((Xib(),a.ac),b);a.f.vf();uFc(a,a.ac);qFc(a);a.d=djb(a.ac);!a.d&&(a.d=a.ac);sFc(a,a.d);a.e=true}\nvar x$d='templateName',y$d='templateContents',z$d='childLocations',A$d='<$1 $2src=\"',B$d='v-customlayout';Veb(1065,1,JNd);_.vc=function Hrc(){Rwc(this.c,N9,c9);Hwc(this.c,hRd,b6);Kwc(this.c,f1,uRd,new Irc);Kwc(this.c,b6,uRd,new Krc);Kwc(this.c,N9,uRd,new Mrc);Pwc(this.c,b6,_Md,new zwc(f1));Pwc(this.c,b6,VMd,new zwc(N9));Erc(this.c);Nwc(this.c,N9,x$d,new zwc(Kbb));Nwc(this.c,N9,y$d,new zwc(Kbb));Nwc(this.c,N9,z$d,new Awc(lSd,DE(Qdb,JRd,8,0,[new zwc(M8),new zwc(Kbb)])));egc((!Zfc&&(Zfc=new kgc),Zfc),this.b.e)};Veb(597,1,ZTd,Irc);_.Pj=function Jrc(a,b){return new xFc};Veb(1160,1,ZTd,Krc);_.Pj=function Lrc(a,b){return new dbd};Veb(1090,1,ZTd,Mrc);_.Pj=function Nrc(a,b){return new Ord};Veb(77,yJd,{2279:1,2536:1,861:1,2171:1,122:1,2560:1,2333:1,248:1,252:1,18:1,77:1,185:1},xFc);_.uf=function yFc(a){throw new Oxd};_.vf=function zFc(){unb(this);this.g.vf();this.b.vf()};_.Ak=function BFc(){};_.Pe=function CFc(a){enb(this,a);if((Xib(),Qkb(a))==bJd){H3b(this,true);Pkb(a,true)}};_.qf=function DFc(){fnb(this);!!this.d&&(this.d.notifyChildrenOfSizeChange=null,undefined)};_.Le=function EFc(a){return tFc(this,a)};_.hf=function FFc(a){Pk((Xib(),this.ac),a);Tmb(this.ac,dVd,true)};_.e=false;Veb(906,2029,{402:1,7:1,825:1,71:1,332:1,62:1,1684:1,162:1,198:1,80:1,410:1,136:1,3:1},dbd);_.xh=function ebd(){return !this.G&&(this.G=wPb(this)),OE(OE(OE(this.G,5),38),163)};_.jh=function fbd(){return !this.G&&(this.G=wPb(this)),OE(OE(OE(this.G,5),38),163)};_.ji=function gbd(){return !this.G&&(this.G=wPb(this)),OE(OE(OE(this.G,5),38),163)};_.Nf=function hbd(){return OE(cQb(this),77)};_.lh=function ibd(){OE(cQb(this),77).c=this.v;OE(cQb(this),77).i=this.A};_.Bi=function jbd(){pFc((OE(cQb(this),77),djb(mmb(OE(cQb(this),77)))))};_.Uh=function kbd(b){var c,d,e,f,g,i;cbd(this);for(d=URb(this).we();d.Yf();){c=OE(d.Zf(),7);e=OE((!this.G&&(this.G=wPb(this)),OE(OE(OE(this.G,5),38),163)).b.vm(c),2);try{vFc(OE(cQb(this),77),c.Nf(),e)}catch(a){a=Seb(a);if(!QE(a,264))throw Reb(a)}}for(g=b.b.we();g.Yf();){f=OE(g.Zf(),7);if(f.hh()==this){continue}i=f.Nf();i.of()&&tFc(OE(cQb(this),77),i)}};_.nh=function lbd(a){eQb(this,a);cbd(this);AFc(OE(cQb(this),77).j);OE(cQb(this),77).j=null};_.Vh=function mbd(a){wFc(OE(cQb(this),77),a)};_.Qh=function nbd(a,b){};_.b=false;Veb(163,38,{5:1,398:1,38:1,163:1,3:1},Ord);var N9=hvd('com.vaadin.shared.ui.customlayout.','CustomLayoutState',163),b6=hvd('com.vaadin.client.ui.customlayout.',iUd,906),f1=hvd(jXd,'VCustomLayout',77),BZ=hvd(XXd,'ConnectorBundleLoaderImpl$13$1$1',597),CZ=hvd(XXd,'ConnectorBundleLoaderImpl$13$1$2',1160),DZ=hvd(XXd,'ConnectorBundleLoaderImpl$13$1$3',1090);BFd(Vh)(13);\n//# sourceURL=gov.va.ehtac.appsonfhir.gwt.HealthElementsWidgetSet-13.js\n")
