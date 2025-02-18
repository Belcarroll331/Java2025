def convert(value, from_unit, to_unit):
    conversions = {
        ('kg', 'lb'): 2.20462,
        ('lb', 'kg'): 0.453592,
        ('g', 'oz'): 0.035274,
        ('oz', 'g'): 28.3495,
        ('km', 'mi'): 0.621371,
        ('mi', 'km'): 1.60934,
        ('mm', 'in'): 0.0393701,
        ('in', 'mm'): 25.4
    }
    
    key = (from_unit, to_unit)
    if key in conversions:
        return value * conversions[key]
    else:
        return None

def main():
    print("Welcome to Metric Converter!\n")
    print("Please input your query. For example, '1 km = mi'.")
    print("Enter 'exit' or '-1' to exit the program.\n")
    
    while True:
        user_input = input("Enter conversion query: ").strip().lower()
        if user_input in ['exit', '-1']:
            print("Exiting the converter. Goodbye!")
            break
        
        try:
            parts = user_input.split()
            if len(parts) != 4 or parts[2] != '=':
                raise ValueError
            
            value = float(parts[0])
            from_unit = parts[1]
            to_unit = parts[3]
            
            result = convert(value, from_unit, to_unit)
            
            if result is not None:
                print(f"{value} {from_unit} is {result:.4f} {to_unit}\n")
            else:
                print("Conversion not supported. Try again with a valid format, e.g., '1 kg = lb'.\n")
        
        except ValueError:
            print("Invalid input format. Use '1 kg = lb' format.\n")

if __name__ == "__main__":
    main()
