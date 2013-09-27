package synccraft.core;

import java.io.File;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;


public class SyncCraftConfiguration extends Configuration {

	public SyncCraftConfiguration(File file) {
		super(file);
	}

	@Override
	public void save() {
		Property versionProp = get(CATEGORY_GENERAL, "Sync Craft", "Sync Craft 0.0.1");
		versionProp.set("0.0.1");
		super.save();
	}

}