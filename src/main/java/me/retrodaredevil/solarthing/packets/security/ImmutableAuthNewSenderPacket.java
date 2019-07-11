package me.retrodaredevil.solarthing.packets.security;

import me.retrodaredevil.solarthing.packets.security.crypto.InvalidKeyException;
import me.retrodaredevil.solarthing.packets.security.crypto.KeyUtil;

import java.security.PublicKey;

public final class ImmutableAuthNewSenderPacket implements AuthNewSenderPacket {
	private final SecurityPacketType packetType = SecurityPacketType.AUTH_NEW_SENDER;
	
	private final String sender;
	private final String publicKey;
	private final transient PublicKey publicKeyObject;
	
	public ImmutableAuthNewSenderPacket(String sender, String publicKey) throws InvalidKeyException {
		this.sender = sender;
		this.publicKey = publicKey;
		publicKeyObject = KeyUtil.decodePublicKey(publicKey);
	}
	
	@Override
	public String getPublicKey() {
		return publicKey;
	}
	
	@Override
	public PublicKey getPublicKeyObject() {
		return publicKeyObject;
	}
	
	@Override
	public SecurityPacketType getPacketType() {
		return packetType;
	}
	
	@Override
	public String getSender() {
		return sender;
	}
}