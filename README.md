# Ride-Share Driver Registration and Validation System

## Overview

This project is part of a ride-sharing platform focused on registering and validating prospective drivers. It includes functionality for driver registration, validation, and querying information about registered drivers.

## Features

1. **Driver Registration**: Prospective drivers provide personal, vehicle, and driving history information.
2. **Validation System**: Validates drivers based on age, license details, vehicle, and insurance information.
3. **Unique Driver Pool**: Stores registered drivers and their vehicles, ensuring no duplicates.
4. **Driver Search**: Allows querying driver details by last name.

## Driver Registration Process

Prospective drivers provide:
- Personal info (name, birthdate)
- License info (number, issuance, expiration)
- Vehicle details (make, model, year)
- Insurance info (owner, expiration)
- Driving and vehicle history (violations, crashes)

## Registration Validation

Drivers are validated on:
- Age (21+), valid license (issued >6 months ago)
- Vehicle age (<15 years)
- Active insurance
- No serious violations or crashes in the last 6 months

## Querying Driver Information

Use `provideDriverInfo(String lastName)` to retrieve driver info, including registered vehicles and any violations.

## Simulation

The `RideshareDriverValidator` class reads a file of prospective drivers, validates them, and allows querying of registered drivers.
