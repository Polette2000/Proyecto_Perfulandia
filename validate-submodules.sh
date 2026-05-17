#!/bin/bash
# Script de validación: Verificar que todos los submódulos están configurados correctamente

echo "================================"
echo "🔍 VALIDACIÓN DE SUBMÓDULOS"
echo "================================"

# Verificar que .gitmodules existe
if [ ! -f .gitmodules ]; then
    echo "❌ ERROR: .gitmodules no encontrado"
    exit 1
fi

echo "✅ .gitmodules encontrado"
echo ""

# Contar submódulos configurados
num_submodules=$(grep -c '\[submodule' .gitmodules)
echo "📊 Total de submódulos configurados: $num_submodules"
echo ""

# Listar submódulos
echo "📋 Submódulos:"
grep '^\[submodule' .gitmodules | sed 's/\[submodule \"/  • /' | sed 's/\"\]//'
echo ""

# Verificar estructura de directorios esperada
echo "🔎 Verificando estructura de directorios..."
expected_dirs=("services" "services/product" "services/inventory" "services/supplier" "services/branch" "services/user" "services/auth" "services/cart" "services/order" "services/billing" "services/shipping")

for dir in "${expected_dirs[@]}"; do
    if [ -d "$dir" ]; then
        echo "  ✅ $dir"
    else
        echo "  ⏳ $dir (aún no inicializado)"
    fi
done

echo ""
echo "================================"
echo "✨ VALIDACIÓN COMPLETADA"
echo "================================"
