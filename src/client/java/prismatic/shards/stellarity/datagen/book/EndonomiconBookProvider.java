package prismatic.shards.stellarity.datagen.book;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.datagen.book.category.ArmorsCategory;

public class EndonomiconBookProvider extends SingleBookSubProvider {
	public EndonomiconBookProvider(ModonomiconLanguageProvider lang) {
		super("endonomicon", Stellarity.MOD_ID, lang);
	}

	@Override
	protected void registerDefaultMacros() {

	}

	@Override
	protected BookModel additionalSetup(BookModel book) {
		super.additionalSetup(book);
		book.withGenerateBookItem(false);
		return book;
	}

	@Override
	protected void generateCategories() {
		this.add(new ArmorsCategory(this).generate());
	}

	@Override
	protected String bookName() {
		return "Endonomicon";
	}

	@Override
	protected String bookTooltip() {
		return "A book that holds the secrets of Stellarity";
	}
}