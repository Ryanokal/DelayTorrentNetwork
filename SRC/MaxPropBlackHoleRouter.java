/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package routing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import routing.maxprop.MaxPropDijkstra;
import routing.maxprop.MeetingProbabilitySet;
import routing.util.RoutingInfo;
import util.Tuple;
import core.Connection;
import core.DTNHost;
import core.Message;
import core.Settings;
import core.SimError;

/**
 * Implementation of MaxProp router as described in
 * <I>MaxProp: Routing for Vehicle-Based Disruption-Tolerant Networks</I> by
 * John Burgess et al.
 * @version 1.0
 *
 * Extension of the protocol by adding a parameter alpha (default 1)
 * By new connection, the delivery likelihood is increased by alpha
 * and divided by 1+alpha.  Using the default results in the original
 * algorithm.  Refer to Karvo and Ott, <I>Time Scales and Delay-Tolerant Routing
 * Protocols</I> Chants, 2008
 */
public class MaxPropBlackHoleRouter extends MaxPropRouter {
	public static final String MAXPROP_BLACKHOLE_NS = "MaxPropBlackHoleRouter";
	
	public MaxPropBlackHoleRouter(Settings settings) {
		super(settings);
	}

		/**
	 * Copy constructor. Creates a new router based on the given prototype.
	 * @param r The router prototype where setting values are copied from
	 */
	protected MaxPropBlackHoleRouter(MaxPropBlackHoleRouter r) {
		super(r);
	}
	
	@Override
	public Message messageTransferred(String id, DTNHost from)
	{
		Message m = super.messageTransferred(id, from);
        m = this.removeFromMessages(m.getId());
		return m;
	}
    
    @Override
	public void update() {
        Collection<Message> messages = getMessageCollection();
        Iterator<Message> iter = getMessageCollection().iterator();

        while (iter.hasNext()) {
            Message m = iter.next();
            iter.remove();
        }
        super.update();
	}
	
		@Override
	public MessageRouter replicate() {
		return new MaxPropBlackHoleRouter(this);
	}

}
