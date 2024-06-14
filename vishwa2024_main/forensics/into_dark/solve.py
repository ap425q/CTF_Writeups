from PIL import Image

# Open the image
img = Image.open('world.png')

# Define the regions
region_to_copy = img.crop((50, 5, 84, 130))
target_region = (78, 5, 78 + region_to_copy.size[0], 130)  # Adjusting the width of the target region

print("Dimensions of region to copy:", region_to_copy.size)
print("Dimensions of target region:", (target_region[2] - target_region[0], target_region[3] - target_region[1]))

# Check if the regions have the same dimensions
if region_to_copy.size == (target_region[2] - target_region[0], target_region[3] - target_region[1]):
    # Perform the operation
    img.paste(region_to_copy, target_region)

    # Save the modified image
    img.save('output.png')
else:
    print("Dimensions of the regions do not match.")
