package com.wxyz;

import java.security.Permission;

public class SystemExitControl {
	 
    public static class ExitTrappedException extends SecurityException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
    }
 
    public static void forbidSystemExitCall() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
 
    public static void enableSystemExitCall() {
        System.setSecurityManager(null);
    }
}