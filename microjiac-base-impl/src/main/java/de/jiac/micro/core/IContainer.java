/*
 * MicroJIAC - A Lightweight Agent Framework
 * This file is part of MicroJIAC Base-Implementation.
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
package de.jiac.micro.core;

import org.slf4j.Logger;

import com.github.libxjava.lang.IClassLoader;


/**
 * A container in MicroJIAC is either a node or an agent. Each one
 * can be customised by adding/removing handles that are identified
 * by their class hierarchy. Furthermore, each container also provides
 * a logger instance.
 * 
 * @author Erdene-Ochir Tuguldur
 * @author Marcel Patzlaff
 * @version $Revision$
 */
public interface IContainer {
    IHandle getHandle(Class handleClass);
    void addHandle(IHandle handle);
    void removeHandle(IHandle handle);
    
    /**
     * Same as {@link #getLogger(String)} with argument {@code null}
     */
    Logger getLogger();
    Logger getLogger(String name);
    IClassLoader getClassLoader();
}
