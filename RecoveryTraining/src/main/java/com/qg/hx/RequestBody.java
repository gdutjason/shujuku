package com.qg.hx;

public class RequestBody {
	public String grant_type = "client_credentials";
	public String client_id = "YXA6EUBz8JbBEeaKc7VFe2p1kg";
	public String client_secret = "YXA6vRgJiYTd2APc2EbHmSY7Dh6CKSo";

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

}
