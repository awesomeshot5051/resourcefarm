//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.nbt.ListTag;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//
//public void loadPickType(CompoundTag compound,) {
//    // Check if the "components" tag exists
//    if (compound.contains("components", CompoundTag.TAG_COMPOUND)) {
//        CompoundTag components = compound.getCompound("components");
//
//        // Check if the "resource_farms:pick_type" tag exists in the components
//        if (components.contains("resource_farms:pick_type", CompoundTag.TAG_LIST)) {
//            ListTag pickTypeList = components.getList("resource_farms:pick_type", CompoundTag.TAG_COMPOUND);
//
//            // Check if there's at least one entry in the list
//            if (!pickTypeList.isEmpty()) {
//                CompoundTag pickTypeTag = pickTypeList.getCompound(0); // Get the first item in the list
//
//                // Retrieve the item details
//                if (pickTypeTag.contains("item", CompoundTag.TAG_COMPOUND)) {
//                    CompoundTag itemTag = pickTypeTag.getCompound("item");
//
//                    // Get the item ID (e.g., minecraft:diamond_pickaxe)
//                    String itemId = itemTag.getString("id");
//                    // Get the item count (e.g., 1)
//                    int count = itemTag.getInt("count");
//
//                    // You can now use the item ID and count as needed
//                    System.out.println("Item ID: " + itemId);  // minecraft:diamond_pickaxe
//                    System.out.println("Count: " + count);     // 1
//
//                    // If you need to create an ItemStack based on the item ID
//                    ItemStack itemStack = new ItemStack(ItemStack.parse(itemId), count);
//                    // Do something with the itemStack if needed
//                }
//            }
//        }
//    }
//}
