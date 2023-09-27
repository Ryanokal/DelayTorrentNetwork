package routing;

import java.util.*;

import core.*;
import util.Tuple;
import java.util.Iterator;  
/**
 * An implementation of Spray and Focus DTN routing as described in 
 * <em>Spray and Focus: Efficient Mobility-Assisted Routing for Heterogeneous
 * and Correlated Mobility</em> by Thrasyvoulos Spyropoulos et al.
 * 
 * @author PJ Dillon, University of Pittsburgh
 */
public class SprayAndFocusBlackHoleRouter extends SprayAndFocusRouter 
{
	public SprayAndFocusBlackHoleRouter(Settings s)
	{
		super(s);
	}
	
	/**
	 * Copy Constructor.
	 * 
	 * @param r The router from which settings should be copied
	 */
	public SprayAndFocusBlackHoleRouter(SprayAndFocusBlackHoleRouter r)
	{
		super(r);
	}
	
	@Override
	public MessageRouter replicate() 
	{
		return new SprayAndFocusBlackHoleRouter(this);
	}


	@Override
	public Message messageTransferred(String id, DTNHost from) {
		Message m = super.messageTransferred(id, from);
        m = this.removeFromMessages(id);
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

}