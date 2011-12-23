# .cshrc
#
# this file gets executed with every subshell

# is this an interactive shell?
if ( $?prompt ) then

    # yes, we're in an interactive shell

    # prompt
    set prompt = "%{\033[1;36m%}peter %{\033[1;35m%}[%~] %{\033[1;36m%}%n \%%{\033[1;33m%} "

    # history
    set history         = 1000
    set savehist        = 1000
    #set EDITOR          = vim
    set EDITOR          = nedit
    set filec fignore = (.o)

    # cmd-line bindings
    bindkey -v          ; # use vi key bindings

    # display
    set DISPLAY         = localhost:0
    #setenv DISPLAY ktppxsolw02:0.0

    # cvs
    # set MAKEFLAGS     = "--no-print-directory"
    set CVSROOT         = :ext:mzieg@cvs-oa.orl.lmco.com:/usr/local/cvsroot
    # aliases
    alias vi            vim
    alias dir           'ls -lav --color=auto'
    alias nslookup      host

endif

bindkey >& /dev/null
if ( $status == 0 ) then
        bindkey "\e[1~" beginning-of-line
        bindkey "\e[3~" delete-char
        bindkey "\e[4~" end-of-line
        bindkey "\e[5~" history-search-backward
        bindkey "\e[6~" history-search-forward
endif

setenv MACHINE x86Linux
setenv ARCH cygwin

# ---------- Arguments des applis Kplus -----------
setenv KPLUS_ARGS       "-Ualbandri -Palbandri12"
setenv KPLUS_ARGS_KPLUS "-Ukplus    -Pkplus11"


# ----------
if ( "${ARCH}" == sun4sol ) then
    coreadm -p core.%f.%n.%p $$
endif

##
# BACKEND
##

setenv KPLUSTP_USER albandri
setenv KPLUSTP_VERSION 30
setenv KPLUSTP_EXTRACTION ktpplus
setenv DEV_HOME /cygdrive/c/workspace/users

setenv WORKSPACE_ENV ${DEV_HOME}/${KPLUSTP_USER}${KPLUSTP_VERSION}/env/${ARCH}

alias 27 'source ${DEV_HOME}/${KPLUSTP_USER}27/env/${ARCH}/dev.env.csh \!*; test ! -f ~/.cshrc.local || source ~/.cshrc.local \!*'
alias 28 'source ${DEV_HOME}/${KPLUSTP_USER}28/env/${ARCH}/dev.env.csh \!*; test ! -f ~/.cshrc.local || source ~/.cshrc.local \!*'
alias 30 'source ${WORKSPACE_ENV}/dev.env.csh \!*; test ! -f ~/.cshrc.local || source ~/.cshrc.local \!*'
