/*!
 * Platform.js <https://mths.be/platform>
 * Copyright 2014-2018 Benjamin Tan <https://bnjmnt4n.now.sh/>
 * Copyright 2011-2013 John-David Dalton <http://allyoucanleet.com/>
 * Available under MIT license <https://mths.be/mit>
 */
(function(){"use strict";var e={function:!0,object:!0},t=e[typeof window]&&window||this,i=e[typeof exports]&&exports,n=e[typeof module]&&module&&!module.nodeType&&module,r=i&&n&&"object"==typeof global&&global;!r||r.global!==r&&r.window!==r&&r.self!==r||(t=r);var o=Math.pow(2,53)-1,a=/\bOpera/,l=Object.prototype,s=l.hasOwnProperty,b=l.toString;function p(e){return(e=String(e)).charAt(0).toUpperCase()+e.slice(1)}function c(e){return e=x(e),/^(?:webOS|i(?:OS|P))/.test(e)?e:p(e)}function u(e,t){for(var i in e)s.call(e,i)&&t(e[i],i,e)}function d(e){return null==e?p(e):b.call(e).slice(8,-1)}function f(e){return String(e).replace(/([ -])(?!$)/g,"$1?")}function S(e,t){var i=null;return function(e,t){var i=-1,n=e?e.length:0;if("number"==typeof n&&n>-1&&n<=o)for(;++i<n;)t(e[i],i,e);else u(e,t)}(e,function(n,r){i=t(i,n,r,e)}),i}function x(e){return String(e).replace(/^ +| +$/g,"")}var h=function e(i){var n=t,r=i&&"object"==typeof i&&"String"!=d(i);r&&(n=i,i=null);var o=n.navigator||{},l=o.userAgent||"";i||(i=l);var s,p,h,m,g,O=r?!!o.likeChrome:/\bChrome\b/.test(i)&&!/internal|\n/i.test(b.toString()),y="Object",M=r?y:"ScriptBridgingProxyObject",v=r?y:"Environment",P=r&&n.java?"JavaPackage":d(n.java),w=r?y:"RuntimeObject",E=/\bJava/.test(P)&&n.java,k=E&&d(n.environment)==v,C=E?"a":"α",W=E?"b":"β",B=n.document||{},I=n.operamini||n.opera,A=a.test(A=r&&I?I["[[Class]]"]:d(I))?A:I=null,R=i,T=[],F=null,G=i==l,$=G&&I&&"function"==typeof I.version&&I.version(),X=S([{label:"EdgeHTML",pattern:"Edge"},"Trident",{label:"WebKit",pattern:"AppleWebKit"},"iCab","Presto","NetFront","Tasman","KHTML","Gecko"],function(e,t){return e||RegExp("\\b"+(t.pattern||f(t))+"\\b","i").exec(i)&&(t.label||t)}),j=S(["Adobe AIR","Arora","Avant Browser","Breach","Camino","Electron","Epiphany","Fennec","Flock","Galeon","GreenBrowser","iCab","Iceweasel","K-Meleon","Konqueror","Lunascape","Maxthon",{label:"Microsoft Edge",pattern:"Edge"},"Midori","Nook Browser","PaleMoon","PhantomJS","Raven","Rekonq","RockMelt",{label:"Samsung Internet",pattern:"SamsungBrowser"},"SeaMonkey",{label:"Silk",pattern:"(?:Cloud9|Silk-Accelerated)"},"Sleipnir","SlimBrowser",{label:"SRWare Iron",pattern:"Iron"},"Sunrise","Swiftfox","Waterfox","WebPositive","Opera Mini",{label:"Opera Mini",pattern:"OPiOS"},"Opera",{label:"Opera",pattern:"OPR"},"Chrome",{label:"Chrome Mobile",pattern:"(?:CriOS|CrMo)"},{label:"Firefox",pattern:"(?:Firefox|Minefield)"},{label:"Firefox for iOS",pattern:"FxiOS"},{label:"IE",pattern:"IEMobile"},{label:"IE",pattern:"MSIE"},"Safari"],function(e,t){return e||RegExp("\\b"+(t.pattern||f(t))+"\\b","i").exec(i)&&(t.label||t)}),N=z([{label:"BlackBerry",pattern:"BB10"},"BlackBerry",{label:"Galaxy S",pattern:"GT-I9000"},{label:"Galaxy S2",pattern:"GT-I9100"},{label:"Galaxy S3",pattern:"GT-I9300"},{label:"Galaxy S4",pattern:"GT-I9500"},{label:"Galaxy S5",pattern:"SM-G900"},{label:"Galaxy S6",pattern:"SM-G920"},{label:"Galaxy S6 Edge",pattern:"SM-G925"},{label:"Galaxy S7",pattern:"SM-G930"},{label:"Galaxy S7 Edge",pattern:"SM-G935"},"Google TV","Lumia","iPad","iPod","iPhone","Kindle",{label:"Kindle Fire",pattern:"(?:Cloud9|Silk-Accelerated)"},"Nexus","Nook","PlayBook","PlayStation Vita","PlayStation","TouchPad","Transformer",{label:"Wii U",pattern:"WiiU"},"Wii","Xbox One",{label:"Xbox 360",pattern:"Xbox"},"Xoom"]),K=S({Apple:{iPad:1,iPhone:1,iPod:1},Archos:{},Amazon:{Kindle:1,"Kindle Fire":1},Asus:{Transformer:1},"Barnes & Noble":{Nook:1},BlackBerry:{PlayBook:1},Google:{"Google TV":1,Nexus:1},HP:{TouchPad:1},HTC:{},LG:{},Microsoft:{Xbox:1,"Xbox One":1},Motorola:{Xoom:1},Nintendo:{"Wii U":1,Wii:1},Nokia:{Lumia:1},Samsung:{"Galaxy S":1,"Galaxy S2":1,"Galaxy S3":1,"Galaxy S4":1},Sony:{PlayStation:1,"PlayStation Vita":1}},function(e,t,n){return e||(t[N]||t[/^[a-z]+(?: +[a-z]+\b)*/i.exec(N)]||RegExp("\\b"+f(n)+"(?:\\b|\\w*\\d)","i").exec(i))&&n}),V=S(["Windows Phone","Android","CentOS",{label:"Chrome OS",pattern:"CrOS"},"Debian","Fedora","FreeBSD","Gentoo","Haiku","Kubuntu","Linux Mint","OpenBSD","Red Hat","SuSE","Ubuntu","Xubuntu","Cygwin","Symbian OS","hpwOS","webOS ","webOS","Tablet OS","Tizen","Linux","Mac OS X","Macintosh","Mac","Windows 98;","Windows "],function(e,t){var n,r,o,a,l=t.pattern||f(t);return!e&&(e=RegExp("\\b"+l+"(?:/[\\d.]+|[ \\w.]*)","i").exec(i))&&(n=e,r=l,o=t.label||t,a={"10.0":"10",6.4:"10 Technical Preview",6.3:"8.1",6.2:"8",6.1:"Server 2008 R2 / 7","6.0":"Server 2008 / Vista",5.2:"Server 2003 / XP 64-bit",5.1:"XP",5.01:"2000 SP1","5.0":"2000","4.0":"NT","4.90":"ME"},r&&o&&/^Win/i.test(n)&&!/^Windows Phone /i.test(n)&&(a=a[/[\d.]+$/.exec(n)])&&(n="Windows "+a),n=String(n),r&&o&&(n=n.replace(RegExp(r,"i"),o)),e=n=c(n.replace(/ ce$/i," CE").replace(/\bhpw/i,"web").replace(/\bMacintosh\b/,"Mac OS").replace(/_PowerPC\b/i," OS").replace(/\b(OS X) [^ \d]+/i,"$1").replace(/\bMac (OS X)\b/,"$1").replace(/\/(\d)/," $1").replace(/_/g,".").replace(/(?: BePC|[ .]*fc[ \d.]+)$/i,"").replace(/\bx86\.64\b/gi,"x86_64").replace(/\b(Windows Phone) OS\b/,"$1").replace(/\b(Chrome OS \w+) [\d.]+\b/,"$1").split(" on ")[0])),e});function z(e){return S(e,function(e,t){var n=t.pattern||f(t);return!e&&(e=RegExp("\\b"+n+" *\\d+[.\\w_]*","i").exec(i)||RegExp("\\b"+n+" *\\w+-[\\w]*","i").exec(i)||RegExp("\\b"+n+"(?:; *(?:[a-z]+[_-])?[a-z]+\\d+|[^ ();-]*)","i").exec(i))&&((e=String(t.label&&!RegExp(n,"i").test(t.label)?t.label:e).split("/"))[1]&&!/[\d.]+/.test(e[0])&&(e[0]+=" "+e[1]),t=t.label||t,e=c(e[0].replace(RegExp(n,"i"),t).replace(RegExp("; *(?:"+t+"[_-])?","i")," ").replace(RegExp("("+t+")[-_.]?(\\w)","i"),"$1 $2"))),e})}if(X&&(X=[X]),K&&!N&&(N=z([K])),(s=/\bGoogle TV\b/.exec(N))&&(N=s[0]),/\bSimulator\b/i.test(i)&&(N=(N?N+" ":"")+"Simulator"),"Opera Mini"==j&&/\bOPiOS\b/.test(i)&&T.push("running in Turbo/Uncompressed mode"),"IE"==j&&/\blike iPhone OS\b/.test(i)?(K=(s=e(i.replace(/like iPhone OS/,""))).manufacturer,N=s.product):/^iP/.test(N)?(j||(j="Safari"),V="iOS"+((s=/ OS ([\d_]+)/i.exec(i))?" "+s[1].replace(/_/g,"."):"")):"Konqueror"!=j||/buntu/i.test(V)?K&&"Google"!=K&&(/Chrome/.test(j)&&!/\bMobile Safari\b/i.test(i)||/\bVita\b/.test(N))||/\bAndroid\b/.test(V)&&/^Chrome/.test(j)&&/\bVersion\//i.test(i)?(j="Android Browser",V=/\bAndroid\b/.test(V)?V:"Android"):"Silk"==j?(/\bMobi/i.test(i)||(V="Android",T.unshift("desktop mode")),/Accelerated *= *true/i.test(i)&&T.unshift("accelerated")):"PaleMoon"==j&&(s=/\bFirefox\/([\d.]+)\b/.exec(i))?T.push("identifying as Firefox "+s[1]):"Firefox"==j&&(s=/\b(Mobile|Tablet|TV)\b/i.exec(i))?(V||(V="Firefox OS"),N||(N=s[1])):!j||(s=!/\bMinefield\b/i.test(i)&&/\b(?:Firefox|Safari)\b/.exec(j))?(j&&!N&&/[\/,]|^[^(]+?\)/.test(i.slice(i.indexOf(s+"/")+8))&&(j=null),(s=N||K||V)&&(N||K||/\b(?:Android|Symbian OS|Tablet OS|webOS)\b/.test(V))&&(j=/[a-z]+(?: Hat)?/i.exec(/\bAndroid\b/.test(V)?V:s)+" Browser")):"Electron"==j&&(s=(/\bChrome\/([\d.]+)\b/.exec(i)||0)[1])&&T.push("Chromium "+s):V="Kubuntu",$||($=S(["(?:Cloud9|CriOS|CrMo|Edge|FxiOS|IEMobile|Iron|Opera ?Mini|OPiOS|OPR|Raven|SamsungBrowser|Silk(?!/[\\d.]+$))","Version",f(j),"(?:Firefox|Minefield|NetFront)"],function(e,t){return e||(RegExp(t+"(?:-[\\d.]+/|(?: for [\\w-]+)?[ /-])([\\d.]+[^ ();/_-]*)","i").exec(i)||0)[1]||null})),(s=("iCab"==X&&parseFloat($)>3?"WebKit":/\bOpera\b/.test(j)&&(/\bOPR\b/.test(i)?"Blink":"Presto"))||/\b(?:Midori|Nook|Safari)\b/i.test(i)&&!/^(?:Trident|EdgeHTML)$/.test(X)&&"WebKit"||!X&&/\bMSIE\b/i.test(i)&&("Mac OS"==V?"Tasman":"Trident")||"WebKit"==X&&/\bPlayStation\b(?! Vita\b)/i.test(j)&&"NetFront")&&(X=[s]),"IE"==j&&(s=(/; *(?:XBLWP|ZuneWP)(\d+)/i.exec(i)||0)[1])?(j+=" Mobile",V="Windows Phone "+(/\+$/.test(s)?s:s+".x"),T.unshift("desktop mode")):/\bWPDesktop\b/i.test(i)?(j="IE Mobile",V="Windows Phone 8.x",T.unshift("desktop mode"),$||($=(/\brv:([\d.]+)/.exec(i)||0)[1])):"IE"!=j&&"Trident"==X&&(s=/\brv:([\d.]+)/.exec(i))&&(j&&T.push("identifying as "+j+($?" "+$:"")),j="IE",$=s[1]),G){if(m="global",g=null!=(h=n)?typeof h[m]:"number",/^(?:boolean|number|string|undefined)$/.test(g)||"object"==g&&!h[m])d(s=n.runtime)==M?(j="Adobe AIR",V=s.flash.system.Capabilities.os):d(s=n.phantom)==w?(j="PhantomJS",$=(s=s.version||null)&&s.major+"."+s.minor+"."+s.patch):"number"==typeof B.documentMode&&(s=/\bTrident\/(\d+)/i.exec(i))?($=[$,B.documentMode],(s=+s[1]+4)!=$[1]&&(T.push("IE "+$[1]+" mode"),X&&(X[1]=""),$[1]=s),$="IE"==j?String($[1].toFixed(1)):$[0]):"number"==typeof B.documentMode&&/^(?:Chrome|Firefox)\b/.test(j)&&(T.push("masking as "+j+" "+$),j="IE",$="11.0",X=["Trident"],V="Windows");else if(E&&(R=(s=E.lang.System).getProperty("os.arch"),V=V||s.getProperty("os.name")+" "+s.getProperty("os.version")),k){try{$=n.require("ringo/engine").version.join("."),j="RingoJS"}catch(e){(s=n.system)&&s.global.system==n.system&&(j="Narwhal",V||(V=s[0].os||null))}j||(j="Rhino")}else"object"==typeof n.process&&!n.process.browser&&(s=n.process)&&("object"==typeof s.versions&&("string"==typeof s.versions.electron?(T.push("Node "+s.versions.node),j="Electron",$=s.versions.electron):"string"==typeof s.versions.nw&&(T.push("Chromium "+$,"Node "+s.versions.node),j="NW.js",$=s.versions.nw)),j||(j="Node.js",R=s.arch,V=s.platform,$=($=/[\d.]+/.exec(s.version))?$[0]:null));V=V&&c(V)}if($&&(s=/(?:[ab]|dp|pre|[ab]\d+pre)(?:\d+\+?)?$/i.exec($)||/(?:alpha|beta)(?: ?\d)?/i.exec(i+";"+(G&&o.appMinorVersion))||/\bMinefield\b/i.test(i)&&"a")&&(F=/b/i.test(s)?"beta":"alpha",$=$.replace(RegExp(s+"\\+?$"),"")+("beta"==F?W:C)+(/\d+\+?/.exec(s)||"")),"Fennec"==j||"Firefox"==j&&/\b(?:Android|Firefox OS)\b/.test(V))j="Firefox Mobile";else if("Maxthon"==j&&$)$=$.replace(/\.[\d.]+/,".x");else if(/\bXbox\b/i.test(N))"Xbox 360"==N&&(V=null),"Xbox 360"==N&&/\bIEMobile\b/.test(i)&&T.unshift("mobile mode");else if(!/^(?:Chrome|IE|Opera)$/.test(j)&&(!j||N||/Browser|Mobi/.test(j))||"Windows CE"!=V&&!/Mobi/i.test(i))if("IE"==j&&G)try{null===n.external&&T.unshift("platform preview")}catch(e){T.unshift("embedded")}else(/\bBlackBerry\b/.test(N)||/\bBB10\b/.test(i))&&(s=(RegExp(N.replace(/ +/g," *")+"/([.\\d]+)","i").exec(i)||0)[1]||$)?(s=[s,/BB10/.test(i)],V=(s[1]?(N=null,K="BlackBerry"):"Device Software")+" "+s[0],$=null):this!=u&&"Wii"!=N&&(G&&I||/Opera/.test(j)&&/\b(?:MSIE|Firefox)\b/i.test(i)||"Firefox"==j&&/\bOS X (?:\d+\.){2,}/.test(V)||"IE"==j&&(V&&!/^Win/.test(V)&&$>5.5||/\bWindows XP\b/.test(V)&&$>8||8==$&&!/\bTrident\b/.test(i)))&&!a.test(s=e.call(u,i.replace(a,"")+";"))&&s.name&&(s="ing as "+s.name+((s=s.version)?" "+s:""),a.test(j)?(/\bIE\b/.test(s)&&"Mac OS"==V&&(V=null),s="identify"+s):(s="mask"+s,j=A?c(A.replace(/([a-z])([A-Z])/g,"$1 $2")):"Opera",/\bIE\b/.test(s)&&(V=null),G||($=null)),X=["Presto"],T.push(s));else j+=" Mobile";(s=(/\bAppleWebKit\/([\d.]+\+?)/i.exec(i)||0)[1])&&(s=[parseFloat(s.replace(/\.(\d)$/,".0$1")),s],"Safari"==j&&"+"==s[1].slice(-1)?(j="WebKit Nightly",F="alpha",$=s[1].slice(0,-1)):$!=s[1]&&$!=(s[2]=(/\bSafari\/([\d.]+\+?)/i.exec(i)||0)[1])||($=null),s[1]=(/\bChrome\/([\d.]+)/i.exec(i)||0)[1],537.36==s[0]&&537.36==s[2]&&parseFloat(s[1])>=28&&"WebKit"==X&&(X=["Blink"]),G&&(O||s[1])?(X&&(X[1]="like Chrome"),s=s[1]||((s=s[0])<530?1:s<532?2:s<532.05?3:s<533?4:s<534.03?5:s<534.07?6:s<534.1?7:s<534.13?8:s<534.16?9:s<534.24?10:s<534.3?11:s<535.01?12:s<535.02?"13+":s<535.07?15:s<535.11?16:s<535.19?17:s<536.05?18:s<536.1?19:s<537.01?20:s<537.11?"21+":s<537.13?23:s<537.18?24:s<537.24?25:s<537.36?26:"Blink"!=X?"27":"28")):(X&&(X[1]="like Safari"),s=(s=s[0])<400?1:s<500?2:s<526?3:s<533?4:s<534?"4+":s<535?5:s<537?6:s<538?7:s<601?8:"8"),X&&(X[1]+=" "+(s+="number"==typeof s?".x":/[.+]/.test(s)?"":"+")),"Safari"==j&&(!$||parseInt($)>45)&&($=s)),"Opera"==j&&(s=/\bzbov|zvav$/.exec(V))?(j+=" ",T.unshift("desktop mode"),"zvav"==s?(j+="Mini",$=null):j+="Mobile",V=V.replace(RegExp(" *"+s+"$"),"")):"Safari"==j&&/\bChrome\b/.exec(X&&X[1])&&(T.unshift("desktop mode"),j="Chrome Mobile",$=null,/\bOS X\b/.test(V)?(K="Apple",V="iOS 4.3+"):V=null),$&&0==$.indexOf(s=/[\d.]+$/.exec(V))&&i.indexOf("/"+s+"-")>-1&&(V=x(V.replace(s,""))),X&&!/\b(?:Avant|Nook)\b/.test(j)&&(/Browser|Lunascape|Maxthon/.test(j)||"Safari"!=j&&/^iOS/.test(V)&&/\bSafari\b/.test(X[1])||/^(?:Adobe|Arora|Breach|Midori|Opera|Phantom|Rekonq|Rock|Samsung Internet|Sleipnir|Web)/.test(j)&&X[1])&&(s=X[X.length-1])&&T.push(s),T.length&&(T=["("+T.join("; ")+")"]),K&&N&&N.indexOf(K)<0&&T.push("on "+K),N&&T.push((/^on /.test(T[T.length-1])?"":"on ")+N),V&&(s=/ ([\d.+]+)$/.exec(V),p=s&&"/"==V.charAt(V.length-s[0].length-1),V={architecture:32,family:s&&!p?V.replace(s[0],""):V,version:s?s[1]:null,toString:function(){var e=this.version;return this.family+(e&&!p?" "+e:"")+(64==this.architecture?" 64-bit":"")}}),(s=/\b(?:AMD|IA|Win|WOW|x86_|x)64\b/i.exec(R))&&!/\bi686\b/i.test(R)?(V&&(V.architecture=64,V.family=V.family.replace(RegExp(" *"+s),"")),j&&(/\bWOW64\b/i.test(i)||G&&/\w(?:86|32)$/.test(o.cpuClass||o.platform)&&!/\bWin64; x64\b/i.test(i))&&T.unshift("32-bit")):V&&/^OS X/.test(V.family)&&"Chrome"==j&&parseFloat($)>=39&&(V.architecture=64),i||(i=null);var L={};return L.description=i,L.layout=X&&X[0],L.manufacturer=K,L.name=j,L.prerelease=F,L.product=N,L.ua=i,L.version=j&&$,L.os=V||{architecture:null,family:null,version:null,toString:function(){return"null"}},L.parse=e,L.toString=function(){return this.description||""},L.version&&T.unshift($),L.name&&T.unshift(j),V&&j&&(V!=String(V).split(" ")[0]||V!=j.split(" ")[0]&&!N)&&T.push(N?"("+V+")":"on "+V),T.length&&(L.description=T.join(" ")),L}();"function"==typeof define&&"object"==typeof define.amd&&define.amd?(t.platform=h,define(function(){return h})):i&&n?u(h,function(e,t){i[t]=e}):t.platform=h}).call(this);