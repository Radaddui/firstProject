package org.zerock.myapp.config;


import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.*;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.stereotype.Component;

@Log4j2
@NoArgsConstructor

@Component
public class SecurityEventListener {

//    ==============================================================
//         1. To Handle authentication Success Events.
//    ==============================================================


    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event){
        log.trace("1. handleAuthenticationSuccess({}) Invoked.", event);
    } // handleAuthenticationSuccess

    @EventListener
    public void handleSessionFixationProtection(SessionFixationProtectionEvent event){
        log.trace("2. handleSessionFixationProtection({}) Invoked.", event);
    } // handleSessionFixationProtection

    @EventListener
    public void handleInteractiveAuthenticationSuccess(InteractiveAuthenticationSuccessEvent event){
        log.trace("3. handleInteractiveAuthenticationSuccess({}) Invoked.", event);
    } // handleInteractiveAuthenticationSuccess




//    ==============================================================
//         2. To Handle Authentication Failed Events.
//    ==============================================================

    @EventListener
    public void handleBadCredential(AuthenticationFailureBadCredentialsEvent event){
        log.trace("1. handleBadCredential({}) Invoked.", event);

    } // handleInteractiveAuthenticationSuccess

    @EventListener
    public void handleDisabled(AuthenticationFailureDisabledEvent event){
        log.trace("2. handleDisabled({}) Invoked.", event);
    } // handleDisabled

    @EventListener
    public void handleExpired(AuthenticationFailureExpiredEvent event){
        log.trace("3. handleExpired({}) Invoked.", event);
    } // handleExpired

    @EventListener
    public void handleLocked(AuthenticationFailureLockedEvent event){
        log.trace("4. handleLocked({}) Invoked.", event);
    } // handleLocked

    @EventListener
    public void handleCredentialsExpired(AuthenticationFailureCredentialsExpiredEvent event){
        log.trace("5. handleCredentialsExpired({}) Invoked.", event);
    } // handleCredentialsExpired

    @EventListener
    public void handleProviderNotFound(AuthenticationFailureProviderNotFoundEvent event){
        log.trace("6. handleProviderNotFound({}) Invoked.", event);
    } // handleProviderNotFound

    @EventListener
    public void handleServiceException(AuthenticationFailureServiceExceptionEvent event){
        log.trace("7. handleServiceException({}) Invoked.", event);
    } // handleServiceException








} // end class
