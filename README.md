# k-Means
Project was realized during **Artificial Intelligence Tools** course in Polish-Japanese Academy of Information Technology.

This project implements a k-Means clustering algorithm from scratch to group data from the Iris dataset.

## Key Features

### Data Handling
- The program reads data from a text file (`iris.txt`), where each line represents a single data vector.

### k-Means Clustering
- The user specifies the number of desired clusters (`k`).
- The algorithm partitions the data into `k` clusters based on the k-Means clustering technique.
- The clustering process involves the following steps:
  1. Initializing `k` cluster centroids.
  2. Assigning each data vector to the nearest centroid.
  3. Updating centroids based on the mean of assigned vectors.
  4. Repeating the assignment and update steps until all vectors are assigned to their nearest centroid and the centroids no longer change.

### Normalization
- The program compares two variants of clustering:
  - **With normalization**: Data vectors are normalized
  - **Without normalization**: Data vectors are used without any processing
