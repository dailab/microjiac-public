/*
 * MicroJIAC - A Lightweight Agent Framework
 * This file is part of MicroJIAC MIDlet-Maven-Plugin.
 *
 * Copyright (c) 2007-2012 DAI-Labor, Technische Universität Berlin
 *
 * This library includes software developed at DAI-Labor, Technische
 * Universität Berlin (http://www.dai-labor.de)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
/*
 * $Id$ 
 */
package de.jiac.micro.reflect.filter;

/**
 * @author Marcel Patzlaff
 * @version $Revision$
 */
public class AgentElementFilter implements IFilter {
    private Class<?> _iAgentElement= null;
    
    public int filter(Class<?> clazz) {
        ClassLoader loader= clazz.getClassLoader();
        if(_iAgentElement == null) {
            _iAgentElement= init("de.jiac.micro.agent.IAgentElement", loader);
        }
        
        int result= NONE;
        if(_iAgentElement.isAssignableFrom(clazz)) {
            result |= PROPERTIES;
        }
        
        return result;
    }
    
    private Class<?> init(String name, ClassLoader loader) {
        try {
            return Class.forName(name, false, loader);
        } catch (Exception e) {
            throw new RuntimeException("could not load class '" + name + "'" , e);
        }
    }
}
