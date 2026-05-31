package prismatic.shards.stellarity.datagen.book.category;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import prismatic.shards.stellarity.datagen.book.entry.EmptyPageEntry;
import prismatic.shards.stellarity.registry.StellarityItems;

public class ArmorsCategory extends CategoryProvider {
	public ArmorsCategory(ModonomiconProviderBase parent) {
		super(parent);
	}

	@Override
	protected String[] generateEntryMap() {
		return new String[]{"______e____________"};
	}

	@Override
	protected void generateEntries() {
		var emptyEntry = this.add(new EmptyPageEntry(this).generate('e'));
	}

	@Override
	protected String categoryName() {
		return "Armors";
	}

	@Override
	protected BookIconModel categoryIcon() {
		return BookIconModel.create(StellarityItems.SHULKER_CHESTPLATE);
	}

	@Override
	public String categoryId() {
		return "armors";
	}
}
