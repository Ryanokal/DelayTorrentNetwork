/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package routing;

import core.Settings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;

import core.DTNHost;
import core.Message;

/**
 * Epidemic message router with drop-oldest buffer and only single transferring
 * connections at a time.
 */
public class EpidemicBlackHoleRouter extends ActiveRouter {

	/**
	 * Constructor. Creates a new message router based on the settings in
	 * the given Settings object.
	 * @param s The settings object
	 */
	public EpidemicBlackHoleRouter(Settings s) {
		super(s);
		//TODO: read&use epidemic router specific settings (if any)
	}

	/**
	 * Copy constructor.
	 * @param r The router prototype where setting values are copied from
	 */
	protected EpidemicBlackHoleRouter(EpidemicBlackHoleRouter r) {
		super(r);
		//TODO: copy epidemic settings here (if any)
	}

	@Override
	public Message messageTransferred(String id, DTNHost from) {
		Message m = super.messageTransferred(id, from);
        m = this.removeFromMessages(id);
		return m;
	}

	@Override
	public EpidemicBlackHoleRouter replicate() {
		return new EpidemicBlackHoleRouter(this);
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
}
