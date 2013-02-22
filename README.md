mobilebroadband
===============

Software tools for the analysis of 3G/4G quality assessment broadband data.

Depends on Pentaho Reporting. To run project, add a sym link by following the procedure below:

1) Download the latest Pentaho Report Designer version from: 

	http://sourceforge.net/projects/jfreereport/files/04.%20Report%20Designer/

	or, for the cutting edge, clone the git repository at:

	https://github.com/pentaho/pentaho-reporting

2) Install somewhere in your HOME directory

	% mkdir $HOME/prd; cd $HOME/prd

	% tar xvf $HOME/prd-ce-<version>-GA.tar.gz

3) Create the symbolic link:

	% cd ./mobilebroadband

	% ln -s $HOME/prd/<PRD-INSTALL>/lib ./lib

4) Compile and run:

	% ant clean; ant

In order to use the github repo behind a corporate firewall:

1) Enable the proxy:

   	  % git config --global http.proxy http://user:passw@company.com:8080

2) When no proxy is needed anymore:

   	  % git config --global --unset http.proxy
