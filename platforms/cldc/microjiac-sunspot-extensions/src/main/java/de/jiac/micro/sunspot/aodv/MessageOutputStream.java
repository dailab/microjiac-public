/*
 * MicroJIAC - A Lightweight Agent Framework
 * This file is part of MicroJIAC SunSPOT-Extensions.
 *
 * Copyright (c) 2007-2011 DAI-Labor, Technische Universität Berlin
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
package de.jiac.micro.sunspot.aodv;

import java.io.IOException;

import com.github.libxjava.io.BufferedOutputStream;
import com.sun.spot.peripheral.radio.ILowPan;
import com.sun.spot.peripheral.radio.LowPanHeader;
import com.sun.spot.peripheral.radio.RadioPacket;

/**
 * @author Marcel Patzlaff
 * @version $Revision:$
 */
public final class MessageOutputStream extends BufferedOutputStream {
    private final static int BUFFER_SIZE= RadioPacket.MIN_PAYLOAD_LENGTH - ILowPan.MAC_PAYLOAD_OFFSET - LowPanHeader.MAX_UNFRAG_HEADER_LENGTH; 
    
    private final ProtocolManager _protocol;
    
    private MessageID _mid;
    
    public MessageOutputStream(ProtocolManager protocol) {
        super(BUFFER_SIZE);
        _protocol= protocol;
    }
    
    public void setMessageID(MessageID mid) {
        _mid= mid;
    }
    
    protected int getPayloadStart() {
        return ProtocolManager.DATA_OFFSET;
    }

    protected void internalWrite(byte[] buffer, int end, boolean flushed) throws IOException {
        _protocol.send(_mid, buffer, end, flushed);
        
        if(flushed) {
            _mid= null;
        }
    }
}
