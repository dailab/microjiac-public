/*
 * MicroJIAC - A Lightweight Agent Framework
 * This file is part of MicroJIAC MIDP-Extensions.
 *
 * Copyright (c) 2007-2012 DAI-Labor, Technische Universität Berlin
 *
 * This library includes software developed at DAI-Labor, Technische
 * Universität Berlin (http://www.dai-labor.de)
 *
 * This library is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * $Id$
 */
package de.jiac.micro.internal.midp.ui;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import de.jiac.micro.core.IAgent;
import de.jiac.micro.core.scope.AgentScope;
import de.jiac.micro.internal.core.AbstractContainerConfiguration;
import de.jiac.micro.internal.core.AbstractNodeComponent;
import de.jiac.micro.midp.ui.IUIAccessor;

/**
 * @author Marcel Patzlaff
 * @version $Revision$
 */
public class MIDPPlatformMonitorComponent extends AbstractNodeComponent {
    private final class MIDPPlatformMonitorAccess implements IUIAccessor {
        protected MIDPPlatformMonitorAccess() {}
        
        public void setAgentUI(Displayable ui, CommandListener listener) {
            monitor.setAgentUI(getDisplayName(), ui, listener);
        }

        public void setCurrent(Displayable ui) {
            monitor.setCurrent(getDisplayName(), ui);
        }
    }
    
    protected MIDPPlatformMonitor monitor;
    private final MIDPPlatformMonitorAccess _access= new MIDPPlatformMonitorAccess();
    
    public void cleanup() {
        monitor= null;
    }

    public void initialise() {
        monitor= new MIDPPlatformMonitor();
    }

    public void start() {
        
    }

    public void stop() {
        
    }
    
    protected String getDisplayName() {
        IAgent agent= AgentScope.getAgentReference();
        AbstractContainerConfiguration conf= (AbstractContainerConfiguration) agent.getHandle(AbstractContainerConfiguration.class);
        String displayName= conf.displayName;
        
        if(displayName == null) {
            displayName= conf.id;
        }
        
        return displayName;
    }

    protected void addHandlesOn(AgentScope agent) {
        monitor.addAgent(getDisplayName());
        agent.getContainerReference().addHandle(_access);
    }

    protected void removeHandlesFrom(AgentScope agent) {
        agent.getContainerReference().removeHandle(_access);
        monitor.removeAgent(getDisplayName());
    }
}
