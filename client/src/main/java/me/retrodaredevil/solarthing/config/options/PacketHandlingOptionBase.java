package me.retrodaredevil.solarthing.config.options;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import me.retrodaredevil.solarthing.annotations.NotNull;
import me.retrodaredevil.solarthing.annotations.Nullable;
import me.retrodaredevil.solarthing.config.request.DataRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("FieldCanBeLocal")
abstract class PacketHandlingOptionBase extends TimeZoneOptionBase implements PacketHandlingOption {
	private static final Logger LOGGER = LoggerFactory.getLogger(PacketHandlingOptionBase.class);

	@JsonProperty
	@JsonPropertyDescription("An array of strings that each represent a database configuration file relative to the program directory.")
	private List<File> databases = null;
	@JsonProperty(value = "source", required = true)
	private String source = "default";
	@JsonProperty(value = "fragment", required = true)
	private int fragment;
	@JsonProperty
	private Integer unique = null;

	@JsonProperty("extra_option_flags")
	private @Nullable List<ExtraOptionFlag> extraOptionFlags;
	@JsonProperty("request")
	private @Nullable List<DataRequester> dataRequesterList;

	@Override
	public @NotNull List<File> getDatabaseConfigurationFiles() {
		List<File> r = databases;
		if(r == null){
			return Collections.emptyList();
		}
		return r;
	}

	@Override
	public @NotNull String getSourceId() {
		return SourceIdValidator.validateSourceId(source);
	}

	@Override
	public int getFragmentId() {
		return fragment;
	}

	@Override
	public Integer getUniqueIdsInOneHour() {
		return unique;
	}

	@Override
	public @NotNull List<ExtraOptionFlag> getExtraOptionFlags() {
		List<ExtraOptionFlag> r = extraOptionFlags;
		if(r == null){
			return Collections.emptyList();
		}
		LOGGER.warn("Using extra_option_flags is deprecated! Please use request instead.");
		return r;
	}
	@Override
	public @NotNull List<DataRequester> getDataRequesterList() {
		List<DataRequester> r = dataRequesterList;
		if (r == null) {
			return Collections.emptyList();
		}
		return r;
	}
}
