import random

# Constants
economy_rows = 35
economy_columns = 9
executive_rows = 6
executive_columns = 4
first_class_rows = 4
first_class_columns = 2

# Calculate total seats in each class
economy_seats = economy_rows * economy_columns
executive_seats = executive_rows * executive_columns
first_class_seats = first_class_rows * first_class_columns

# Occupancy percentages
economy_occupancy = 0.7
executive_occupancy = 0.5
first_class_occupancy = 0.5

# Calculate booked seats
economy_booked = int(economy_seats * economy_occupancy)
executive_booked = int(executive_seats * executive_occupancy)
first_class_booked = int(first_class_seats * first_class_occupancy)

# Initialize all possible seat labels
economy_seat_labels = [f"{row}{col}" for row in range(1, economy_rows + 1) for col in "ABCDEFGHI"]
executive_seat_labels = [f"{row}{col}" for row in range(36, 36 + executive_rows) for col in "ABCD"]
first_class_seat_labels = [f"{row}{col}" for row in range(42, 42 + first_class_rows) for col in "AB"]

# Shuffle and select booked seats
random.shuffle(economy_seat_labels)
random.shuffle(executive_seat_labels)
random.shuffle(first_class_seat_labels)

booked_seats = (
    economy_seat_labels[:economy_booked] +
    executive_seat_labels[:executive_booked] +
    first_class_seat_labels[:first_class_booked]
)

# Write the booked seats to a CSV file
with open("seatmap.csv", "w") as file:
    for seat in booked_seats:
        file.write(seat + ",\n")

print("CSV file 'seatmap.csv' has been generated with booked seats.")
