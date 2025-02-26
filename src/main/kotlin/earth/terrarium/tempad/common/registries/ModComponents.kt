package earth.terrarium.tempad.common.registries

import com.mojang.serialization.Codec
import com.teamresourceful.bytecodecs.base.ByteCodec
import com.teamresourceful.resourcefullib.common.bytecodecs.ExtraByteCodecs
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry
import com.teamresourceful.resourcefullibkt.common.getValue
import earth.terrarium.tempad.Tempad
import earth.terrarium.tempad.common.config.CommonConfigCache
import earth.terrarium.tempad.common.data.OrganizationMethod
import earth.terrarium.tempad.common.utils.*
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.common.MutableDataComponentHolder

object ModComponents {
    val registry: ResourcefulRegistry<DataComponentType<*>> =
        ResourcefulRegistries.create(BuiltInRegistries.DATA_COMPONENT_TYPE, Tempad.MOD_ID)

    val defaultApp: DataComponentType<ResourceLocation> by registry.register("default_app") {
        componentType {
            serialize = ResourceLocation.CODEC
            networkSerialize = ExtraByteCodecs.RESOURCE_LOCATION
        }
    }

    val defaultMacro: DataComponentType<ResourceLocation> by registry.register("default_macro") {
        componentType {
            serialize = ResourceLocation.CODEC
            networkSerialize = ExtraByteCodecs.RESOURCE_LOCATION
        }
    }

    val organizationMethod: DataComponentType<OrganizationMethod> by registry.register("organization_method") {
        componentType {
            serialize = OrganizationMethod.CODEC
            networkSerialize = OrganizationMethod.BYTE_CODEC
        }
    }

    val FUEL_CHARGES: DataComponentType<Int> by registry.register("fuel_charges") {
        componentType {
            serialize = Codec.INT
            networkSerialize = ByteCodec.INT
        }
    }

    val TEMPAD_FUEL_TYPE: DataComponentType<ResourceLocation> by registry.register("fuel_type") {
        componentType {
            serialize = ResourceLocation.CODEC
            networkSerialize = ExtraByteCodecs.RESOURCE_LOCATION
        }
    }

    val ENERGY_CONSUME_AMOUNT: DataComponentType<Int> by registry.register("energy_consume_amount") {
        componentType {
            serialize = Codec.INT
            networkSerialize = ByteCodec.INT
        }
    }

    val EXPERIENCE_CONSUME_AMOUNT: DataComponentType<Int> by registry.register("experience_consume_amount") {
        componentType {
            serialize = Codec.INT
            networkSerialize = ByteCodec.INT
        }
    }
}

var MutableDataComponentHolder.fuelCharges by ModComponents.FUEL_CHARGES.default(0)

var MutableDataComponentHolder.fuelType by ModComponents.TEMPAD_FUEL_TYPE

var MutableDataComponentHolder.energyConsumeAmount by ModComponents.ENERGY_CONSUME_AMOUNT.default(CommonConfigCache.energyPerCharge)

var MutableDataComponentHolder.experienceConsumeAmount by ModComponents.EXPERIENCE_CONSUME_AMOUNT.default(CommonConfigCache.expPerCharge)

var MutableDataComponentHolder.defaultApp by ModComponents.defaultApp.default(ModApps.teleport)

var MutableDataComponentHolder.defaultMacro by ModComponents.defaultMacro.default(ModMacros.teleportToPinned)

var MutableDataComponentHolder.organizationMethod by ModComponents.organizationMethod.default(OrganizationMethod.DIMENSION)