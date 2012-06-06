# ~/.bashrc: executed by bash(1) for non-login shells.
# see /usr/share/doc/bash/examples/startup-files (in the package bash-doc)
# for examples

####################
### READ ARGUMENTS
####################
TOOLS_OPTION_PURIFY=""
export PERLGEN_OPTION=""
typeset PURIFY=$1
if [ "$1" != "" ]
then

  case "$1" in
    purify)
      TOOLS_OPTION_PURIFY="purify"
      export PERLGEN_OPTION="-p"
    ;;

    *)
      DATABASE_CONFIG=$1
    ;;

  esac

fi
###################

if [ -z "$PROJECT_USER" ]
then
  echo "ERROR: Set PROJECT_USER environment variable!"
  exit 1
fi

if [ -z "$PROJECT_VERSION" ]
then
  echo "ERROR: Set PROJECT_VERSION environment variable!"
  exit 1
fi

if [ -z "$DEV_HOME" ]
then
  echo "ERROR: Set DEV_HOME environment variable!"
  exit 1
fi

if [ -z "$WORKSPACE_ENV" ]
then
  echo "ERROR: Set WORKSPACE_ENV environment variable!"
  exit 1
fi

if [ -z "$PROJECT_EXTRACTION" ]
then
  export PROJECT_EXTRACTION=nabla
fi

if [ -z "$TERM" ]
then
  export TERM=xterm
fi

if [ -z "$EDITOR" ]
then
  export EDITOR=[[EDITOR]]
fi
export SVN_EDITOR=${EDITOR}

export PROJECT_VERSION=10
export PROJECT_MAJOR_VERSION=${PROJECT_VERSION}

export PROJECT_BUILD_TYPE=target
#export CLIENT_SERVER_TYPE=weblogic
export CLIENT_SERVER_TYPE=jboss

export PROJECT_USER_PROFILE="${PROJECT_DEV}/env/config/profiles/${UNIX_USERNAME}.dev.properties"

export PROJECT_DEV=${DEV_HOME}/${PROJECT_USER}${PROJECT_MAJOR_VERSION}
export PROJECT_SRC=${PROJECT_DEV}/${PROJECT_EXTRACTION}
export PROJECT_OBJ=${DRIVE_PATH}/${PROJECT_BUILD_TYPE}
export PROJECT_THIRDPARTY_PATH=${DRIVE_PATH}/thirdparty
export PROJECT_RELEASE=${PROJECT_DEV}/${PROJECT_EXTRACTION}/release/${PROJECT_MAJOR_VERSION}
export PROJECT_PKG=${PROJECT_DEV}/${PROJECT_EXTRACTION}/pkg/${PROJECT_MAJOR_VERSION}

export QTDIR=/lib/qt3

export HUDSON_HOME=${DRIVE_PATH}/hudson
export SVN_HOME=${DRIVE_PATH}/cygwin/bin
export ORACLE_HOME=${DRIVE_PATH}/oraclexe/app/oracle/product/10.2.0/server
export SYBASE_HOME=${DRIVE_PATH}/Sybase/OCS-15_0/
export CYGWIN_HOME=${DRIVE_PATH}/cygwin

export LUMBERMILL_HOME="${DRIVE_PATH}/lumbermill-2.0-b3"
echo "Lumbermill port is 4430"

if [ ! -d $PROJECT_DEV ]
then
  echo "ERROR: Directory ${PROJECT_DEV} doesn't exist!"
  #exit 1
fi

##
# Cleaning LD_LIBRARY_PATH LIBPATH and CLASSPATH
##
##if [ -z "$KEEP_ENV" ]
##then
##
##  if [ -z "$CLASSPATH" ]
##  then
##fix javadoc plugins error
    echo 'Cleaning $CLASSPATH: '${CLASSPATH}
    export CLASSPATH=""
##  fi
##
##  if [ -z "$LD_LIBRARY_PATH" ]
##  then
##    echo 'Cleaning $LD_LIBRARY_PATH: '${LD_LIBRARY_PATH}
##    export LD_LIBRARY_PATH=""
##  fi
##
##  if [ -z "$LIBPATH" ]
##  then
##    echo 'Cleaning $LIBPATH: '${LIBPATH}
##    export LIBPATH=""
##  fi
##fi

if [ -z "$LD_LIBRARY_PATH" ]
then
  export LD_LIBRARY_PATH
fi

###
# Alias
###
alias status="svn status -u"
alias Grep="find . -name '*.[jch]*' -exec grep -n \!* {} + -o -name '.svn' -prune -type f"
alias hGrep="find . -name '*.h' -exec grep -n \!* {} + -o -name '.svn' -prune -type f"
alias myGrep="find . -name '*.*' -exec grep -n \!* {} + -o -name '.svn' -prune -type f"
echo "find . -type d -name ".svn"  -print | xargs rm -Rf"
echo "find . -name 'pom.xml' | xargs grep SNAPSHOT"

###
# THIRDPARTIES
###

export ORB_VERSION=1_3
export ORB=tao

export CORBA_ROOT=${PROJECT_THIRDPARTY_PATH}/tao
export ACE_ROOT=${CORBA_ROOT}/ACE_wrappers

echo ${ACE_ROOT}

TAO_ROOT=${ACE_ROOT}/TAO
export TAO_ROOT

echo ${TAO_ROOT}

MPC_ROOT=${ACE_ROOT}/MPC
export MPC_ROOT

echo ${MPC_ROOT}

CIAO_ROOT=${TAO_ROOT}/CIAO
export CIAO_ROOT
DANCE_ROOT=${CIAO_ROOT}/DANCE
export DANCE_ROOT

DDS_ROOT=${CIO_ROOT}/connectors/dds4ccm
export DDS_ROOT

export BOOST_VERSION=1.41.0
export GETTEXT_VERSION=0.14.5
export XERCES_VERSION=2_5_0
export XML2_VERSION=2.7.4
export TIBRV_VERSION=7.2.x

#export MINGW_ROOT=${DRIVE_PATH}/MinGW

export BOOST_ROOT=${PROJECT_THIRDPARTY_PATH}/boost/${BOOST_VERSION}
export BOOST=$BOOST_ROOT

#export CMAKE_HOME=${DRIVE_PATH}/CMake-[[CMAKE_MAJOR_VERSION]]
export CMAKE_HOME=${DRIVE_PATH}/cygwin/usr/share/cmake-2.6.4
export CMAKE_ROOT=${CMAKE_HOME}

#CORBA TAO
export PATH=/usr/local/bin:/usr/sbin:/usr/bin:/bin
export PATH=${PATH}:${ACE_ROOT}/tao:${ACE_ROOT}/bin:${ACE_ROOT}/lib:${ACE_ROOT}/TAO/orbsvcs:${MPC_ROOT}:${CIAO_ROOT}:${DANCE_ROOT}:${DDS_ROOT}
export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}::${ACE_ROOT}/tao:${ACE_ROOT}/lib:${ACE_ROOT}/TAO/orbsvcs:${MPC_ROOT}:${CIAO_ROOT}:${DANCE_ROOT}:${DDS_ROOT}

# JAVA
#JDK_HOME=/usr/lib/jvm/ia32-java-6-sun-1.6.0.06/
#JDK_HOME=${DRIVE_PATH}/Sun/SDK/jdk
ln -s ${DRIVE_PATH}/Program\ Files\ \(x86\) /ProgramFilesx86
#export JAVA_HOME="${DRIVE_PATH}/Program Files (x86)/Java/jdk1.5.0_22"
##export JAVA_HOME="/ProgramFilesx86/Java/jdk1.5.0_22"
export JAVA_HOME="${DRIVE_PATH}/Sun/SDK/jdk"
export JRE_HOME=${JAVA_HOME}/jre
#export JDK_HOME JRE_HOME JAVA_HOME
#export JAVA=$JAVA_HOME/bin/java
#PATH=${JDK_HOME}/bin:${JRE_HOME}/bin:${PATH}
PATH=${JAVA_HOME}/bin:${PATH}
export PATH

export JAVA_OPTS="-Xms256m -Xmx1548m"

# MAVEN
#export M2_HOME="${DRIVE_PATH}/apache-maven-3.0.2"
export M2_HOME="${DRIVE_PATH}/apache-maven-2.2.1"
export M2=${M2_HOME}/bin
export MAVEN_OPTS="-Xms512m -Xmx1024m"
#export MAVEN_OPTS="-Xmx512M -XX:MaxPermSize=1024M"
export PATH=${M2}:$PATH
export M2_REPO=${DRIVE_PATH}/repo
echo "Maven repo are in : ${M2_REPO}"

# ANT 
export ANT_HOME="${DRIVE_PATH}/apache-ant-1.8.0"
export ANT_OPTS="-Xmx512m"
export PATH=${ANT_HOME}/bin:${ANT_HOME}/lib:$PATH

# JBOSS
export JBOSS_HOME="${DRIVE_PATH}/jboss-4.2.2.GA"
export PATH=${JBOSS_HOME}/bin:$PATH

export CATALINA_HOME=${JBOSS_HOME}/server/default
#export CATALINA_OPTS="-Dappserver.home=$CATALINA_HOME -Dappserver.base=$CATALINA_HOME"
alias jboss='${JBOSS_HOME}/bin/run.sh > ${PROJECT_DEV}/jboss.txt'

# ECLIPSE
export ECLIPSE_HOME=${DRIVE_PATH}/eclipse-3.7
export PATH=${ECLIPSE_HOME}:$PATH
alias eclipse='${ECLIPSE_HOME}/eclipse'

# LUMBERMILL
export LUMBERMILL_HOME="${DRIVE_PATH}/lumbermill-2.0-b3"
export PATH=${LUMBERMILL_HOME}/bin:${PATH}
alias lumbermill='java -jar ${LUMBERMILL_HOME}/dist/lib/lumbermill.jar'
echo "Lumbermill port is 4430"

# GRAPHVIZ
export GRAPHVIZ_HOME=${DRIVE_PATH}/Graphviz2.26.3
export PATH=${GRAPHVIZ_HOME}/bin:${PATH}

# TIBCO
export TIBCO_HOME=${DRIVE_PATH}/Tibco/Tibrv
export PATH=${TIBCO_HOME}/bin:${PATH}

# WINDOWS
export PATH=$PATH:${DRIVE_PATH}/Windows/system32:${DRIVE_PATH}/Windows

###
# INCLUDE LANGUAGE SPECIFIC
###

# Make a directory with link to several libraries for LIBPATH length restriction
#################################################################################
LIB_LINK_DIR="${PROJECT_OBJ}/lib/${ARCH}"
echo $LIB_LINK_DIR
test ! -d ${LIB_LINK_DIR} && mkdir -p ${LIB_LINK_DIR}
#\rm -f ${LIB_LINK_DIR}/* |& cat >/dev/null
#rm -f ${LIB_LINK_DIR}/*

#ln -s ${PROJECT_THIRDPARTY_PATH}/boost/${BOOST_VERSION}/lib/${ARCH}/opt/shared ${LIB_LINK_DIR}/boost
#ln -s ${PROJECT_THIRDPARTY_PATH}/cppunit/${CPPUNIT_VERSION}/${ARCH}/lib ${LIB_LINK_DIR}/cppunit
#ln -s ${PROJECT_THIRDPARTY_PATH}/gnu/gettext/${GETTEXT_VERSION}/${ARCH}/lib ${LIB_LINK_DIR}/gettext
#ln -s ${PROJECT_THIRDPARTY_PATH}/xml/xerces/c++/${XERCES_VERSION}/${ARCH}/lib ${LIB_LINK_DIR}/xerces
#ln -s ${PROJECT_THIRDPARTY_PATH}/libxml2/${XML2_VERSION}/${ARCH}/lib ${LIB_LINK_DIR}/xml2
#ln -s ${PROJECT_THIRDPARTY_PATH}/tibco/tibrv/${TIBRV_VERSION}/${ARCH}/lib ${LIB_LINK_DIR}/tibrv

#ln -s ${PROJECT_THIRDPARTY_PATH}/database/sybase/openclient/12.51/ESD_17/${ARCH}/lib ${LIB_LINK_DIR}/sybase

#################################################################################

case ${MACHINE} in
    sun4sol)
      export LD_LIBRARY_PATH=/usr/lib/lwp
    ;;
    rs6000)
      export LD_LIBRARY_PATH=${PROJECT_OBJ}/lib/${ARCH}/opt/shared:${PROJECT_OBJ}/lib/${ARCH}/opt
    ;;
    *)
      export LD_LIBRARY_PATH=""
    ;;
esac

#export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:${LIB_LINK_DIR}/tibrv
#export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:${LIB_LINK_DIR}/sybase
#export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:${LIB_LINK_DIR}/oracle

#if [ "$ORB" = "tao" ]
#then
#  export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:${PROJECT_THIRDPARTY_PATH}/corba/${ORB}/${ORB_VERSION}/lib/${ARCH}.mt/opt/shared
#else
#  export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:${PROJECT_THIRDPARTY_PATH}/corba/${ORB}/${ORB_VERSION}/${ARCH}/lib
#fi

#export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:${LIB_LINK_DIR}/boost
#export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:${LIB_LINK_DIR}/cppunit
#export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:${LIB_LINK_DIR}/gettext
#export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:${LIB_LINK_DIR}/xerces

export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:/lib:/usr/lib

##
# Alias
##
alias cde="cd ${PROJECT_DEV}/${PROJECT_EXTRACTION}"
alias cdr="cd ${PROJECT_DEV}"
alias cdc="cd ${PROJECT_DEV}/env/config"
alias cdinc="cd ${PROJECT_OBJ}/include/${ARCH}"
alias cdobj="cd ${PROJECT_OBJ}"
alias cdbin="cd ${PROJECT_OBJ}/bin"

alias cdrl="cd ${PROJECT_RELEASE}/latest"
alias cdri="cd ${PROJECT_RELEASE}/installed"
alias cdcu="cd ${PROJECT_RELEASE}/current"

alias cdcore="cd ${PROJECT_OBJ}/corefiles"
alias ..="cd .."
alias cls="clear"

alias ls='/bin/ls -F'

if [ "${ARCH}" = sun4sol -o "${ARCH}" = x86sol ]
then
  alias l='/bin/ls -Fl'
  alias pp='/usr/ucb/ps -auxwww'
  alias m='/usr/ucb/more'
else
  if [ "${ARCH}" = sun4 ]
  then
    alias l='/bin/ls -Flg'
    alias pp='/usr/bin/ps -auxwww'
  else
    if [ "${ARCH}" = rs6000 ]
    then
      alias l='/bin/ls -Fl'
      alias pp='/usr/bin/ps auxwww'
      alias m='/usr/bin/more'
    else
      if [ "${ARCH}" = hprisc ]
      then
        alias l='/bin/ls -Fl'
        alias pp='/bin/ps -edf'
      else
        if [ "${ARCH}" = x86Linux -o "${ARCH}" = cygwin ]
        then
          alias l='/bin/ls -Fl --color'
          alias pp='/bin/ps -auxwww'
        fi
      fi
    fi
  fi
fi

alias lt='l -t'
alias lrt='l -rt'
alias l~='l ~'
alias ll='l -a'
alias la='l -al'
alias llt='ll -t'
alias llrt='ll -rt'
alias ll~='ll ~'

alias psg="pp | egrep -i \!* |& grep -v 'egrep -i \!*'"
alias psuser="pp | cut -d' ' -f1 | sort | grep -v USER | uniq -c | sort -r"

alias setEnvFiles="${WORKSPACE_ENV}/config/setEnvFiles.sh ${PROJECT_USER_PROFILE} \!* --userdev"
alias setEnvFilesAll="${WORKSPACE_ENV}/config/setEnvFiles.allUserDev.sh ${PROJECT_USER_PROFILE}"

alias setWorkspace="source ${WORKSPACE_ENV}/scripts/setWorkspace.sh"

export M2_SETTINGS=${WORKSPACE_ENV}/home/.m2/settings.xml
#alias mvn="mvn -s ${M2_SETTINGS}"
#echo "Maven settings are in : ${M2_SETTINGS}"

# PATH Setting
#source ${WORKSPACE_ENV}/java/dev.env.sh
#source ${WORKSPACE_ENV}/cpp/dev.env.sh

alias replace="${WORKSPACE_ENV}/scripts/replace.pl"
alias svndi="svn di --diff-cmd=svndiff"

case ${MACHINE} in
  sun4sol)
    test -f /usr/xpg4/bin/grep && alias grep /usr/xpg4/bin/grep
    export PATH=${PATH}:/usr/X/bin
  ;;

  x86Linux)
    test "$TERM" = "xterm" && export TERM=xterm-color
  ;;

  rs6000)
    export PATH=${PATH}:/usr/X11R6/bin
    export LIBPATH=${LD_LIBRARY_PATH}
  ;;

  *)
  ;;

esac

####### TRY TO CHANGE PATH TO BE IN THE CURRENT ENVIRONMENT DEVELOPMENT PATH

OLD_PATH=`pwd`
NEW_PATH=`echo ${OLD_PATH} | sed -e "s/\/${PROJECT_USER}[^\/]*/\/${PROJECT_USER}${PROJECT_VERSION}/"`
        
if [ "${OLD_PATH}" != "${NEW_PATH}" ]
then
  if [ -d "${NEW_PATH}" ]
  then
        cd ${NEW_PATH}
        echo Current path UPDATED : cd `pwd`
  else
        echo "Current path : `pwd`"
  fi
fi

export DISPLAY=localhost:0.0

echo "PATH is ${PATH}"
