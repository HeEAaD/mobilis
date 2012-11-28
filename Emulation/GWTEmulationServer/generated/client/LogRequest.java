package de.tudresden.inf.rn.mobilis.emulation.clientstub;

import org.xmlpull.v1.XmlPullParser;import java.util.List;import java.util.ArrayList;

public class LogRequest extends XMPPBean {

	private String appNamespace = null;
	private String instanceId = null;


	public LogRequest( String appNamespace, String instanceId ) {
		super();
		this.appNamespace = appNamespace;
		this.instanceId = instanceId;

		this.setType( XMPPBean.TYPE_GET );
	}

	public LogRequest(){
		this.setType( XMPPBean.TYPE_GET );
	}


	@Override
	public void fromXML( XmlPullParser parser ) throws Exception {
		boolean done = false;
			
		do {
			switch (parser.getEventType()) {
			case XmlPullParser.START_TAG:
				String tagName = parser.getName();
				
				if (tagName.equals(getChildElement())) {
					parser.next();
				}
				else if (tagName.equals( "appNamespace" ) ) {
					this.appNamespace = parser.nextText();
				}
				else if (tagName.equals( "instanceId" ) ) {
					this.instanceId = parser.nextText();
				}
				else if (tagName.equals("error")) {
					parser = parseErrorAttributes(parser);
				}
				else
					parser.next();
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals(getChildElement()))
					done = true;
				else
					parser.next();
				break;
			case XmlPullParser.END_DOCUMENT:
				done = true;
				break;
			default:
				parser.next();
			}
		} while (!done);
	}

	public static final String CHILD_ELEMENT = "LogRequest";

	@Override
	public String getChildElement() {
		return CHILD_ELEMENT;
	}

	public static final String NAMESPACE = "emulation:iq:log";

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public XMPPBean clone() {
		LogRequest clone = new LogRequest( appNamespace, instanceId );
		clone.cloneBasicAttributes( clone );

		return clone;
	}

	@Override
	public String payloadToXML() {
		StringBuilder sb = new StringBuilder();

		sb.append( "<appNamespace>" )
			.append( this.appNamespace )
			.append( "</appNamespace>" );

		sb.append( "<instanceId>" )
			.append( this.instanceId )
			.append( "</instanceId>" );

		sb = appendErrorPayload(sb);

		return sb.toString();
	}


	public String getAppNamespace() {
		return this.appNamespace;
	}

	public void setAppNamespace( String appNamespace ) {
		this.appNamespace = appNamespace;
	}

	public String getInstanceId() {
		return this.instanceId;
	}

	public void setInstanceId( String instanceId ) {
		this.instanceId = instanceId;
	}

}