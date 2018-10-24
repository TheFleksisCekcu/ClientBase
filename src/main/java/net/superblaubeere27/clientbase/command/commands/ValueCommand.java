package net.superblaubeere27.clientbase.command.commands;

import net.superblaubeere27.clientbase.ClientBase;
import net.superblaubeere27.clientbase.command.Command;
import net.superblaubeere27.clientbase.command.CommandException;
import net.superblaubeere27.clientbase.utils.ChatUtils;
import net.superblaubeere27.clientbase.valuesystem.Value;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ValueCommand extends Command {
    public ValueCommand() {
        super("value", "val", "v", "set");
    }

    @Override
    public void run(String alias, @NotNull String[] args) {
        if (args.length < 3) {
            throw new CommandException("Usage: ." + alias + " <owner> <name> <value>");
        }

        Value value = ClientBase.INSTANCE.valueManager.get(args[0], args[1]);

        if (value == null) {
            throw new CommandException("Value '" + args[0] + "/" + args[1] + "' doesn't exist");
        }

        String val = args[2];

        if (value.getObject() instanceof Boolean) {
            boolean newVal = false;
            boolean ok = false;

            if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("on") || val.equalsIgnoreCase("1")) {
                newVal = true;
                ok = true;
            }
            if (val.equalsIgnoreCase("false") || val.equalsIgnoreCase("off") || val.equalsIgnoreCase("0")) {
                newVal = true;
                ok = true;
            }

            if (ok) {
                value.setObject(newVal);
                ChatUtils.success(ChatUtils.SECONDARY_COLOR + args[0] + "/" + args[1] + ChatUtils.PRIMARY_COLOR + " was set to " + ChatUtils.SECONDARY_COLOR + val);
            } else {
                throw new CommandException(val + " is not valid (allowed: true, false)");
            }
        }
        if (value.getObject() instanceof Integer) {
            try {
                value.setObject(Integer.parseInt(val));
                ChatUtils.success(ChatUtils.SECONDARY_COLOR + args[0] + "/" + args[1] + ChatUtils.PRIMARY_COLOR + " was set to " + ChatUtils.SECONDARY_COLOR + val);
            } catch (NumberFormatException e) {
                throw new CommandException("'" + val + " is not a valid int");
            }
        }
        if (value.getObject() instanceof Long) {
            try {
                value.setObject(Long.parseLong(val));
                ChatUtils.success(ChatUtils.SECONDARY_COLOR + args[0] + "/" + args[1] + ChatUtils.PRIMARY_COLOR + " was set to " + ChatUtils.SECONDARY_COLOR + val);
            } catch (NumberFormatException e) {
                throw new CommandException("'" + val + " is not a valid long");
            }
        }
        if (value.getObject() instanceof Float) {
            try {
                value.setObject(Float.parseFloat(val));
                ChatUtils.success(ChatUtils.SECONDARY_COLOR + args[0] + "/" + args[1] + ChatUtils.PRIMARY_COLOR + " was set to " + ChatUtils.SECONDARY_COLOR + val);
            } catch (NumberFormatException e) {
                throw new CommandException("'" + val + " is not a valid float");
            }
        }
        if (value.getObject() instanceof Double) {
            try {
                value.setObject(Double.parseDouble(val));
                ChatUtils.success(ChatUtils.SECONDARY_COLOR + args[0] + "/" + args[1] + ChatUtils.PRIMARY_COLOR + " was set to " + ChatUtils.SECONDARY_COLOR + val);
            } catch (NumberFormatException e) {
                throw new CommandException("'" + val + " is not a valid double");
            }
        }
    }

    @NotNull
    @Override
    public List<String> autocomplete(int arg, String[] args) {
        return new ArrayList<>();
    }
}
